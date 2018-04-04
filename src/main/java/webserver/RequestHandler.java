package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.controller.HttpController;
import webserver.http.HttpRequest;
import webserver.http.HttpRequestHelper;
import webserver.http.HttpRequestHelperImpl;
import webserver.http.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class RequestHandler implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	private static final HttpRequestHelper httpRequestHelper = new HttpRequestHelperImpl();
	private static final HttpController httpController = new HttpController();

	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	@Override
	public void run() {
		log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
			connection.getPort());

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {

			DataOutputStream dos = new DataOutputStream(out);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			HttpRequest httpRequest = httpRequestHelper.create(bufferedReader);
			HttpResponse httpResponse = httpController.action(httpRequest);

			flushResponse(dos, httpResponse);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void flushResponse(DataOutputStream dos, HttpResponse httpResponse) {
		try {
			dos.writeBytes(httpResponse.getStatus().getLine());
			for (String header : httpResponse.getHeaderLines()) {
				dos.writeBytes(header);
			}

			dos.writeBytes("\r\n");

			if (Objects.nonNull(httpResponse.getBody())) {
				dos.write(httpResponse.getBody(), 0, httpResponse.getBody().length);
			}
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
