package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.controller.HttpController;
import webserver.http.HttpRequest;
import webserver.http.HttpRequestHelper;
import webserver.http.HttpRequestHelperImpl;
import webserver.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

			HttpRequest httpRequest =  httpRequestHelper.create(in);
			HttpResponse httpResponse = new HttpResponse(out);
			httpController.action(httpRequest, httpResponse);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
