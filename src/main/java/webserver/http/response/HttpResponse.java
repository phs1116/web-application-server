package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class HttpResponse {
	private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);
	private DataOutputStream dos;

	private HttpStatusCode status;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String> cookies = new HashMap<>();
	private byte[] body;

	public HttpResponse(OutputStream outputStream) {
		this.dos = new DataOutputStream(outputStream);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void addHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void addCookies(Map<String, String> cookies) {
		this.cookies.putAll(cookies);
	}

	public HttpStatusCode getStatus() {
		return status;
	}

	public void setStatus(HttpStatusCode status) {
		this.status = status;
	}

	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public String getHeader(String key) {
		return this.headers.get(key);
	}

	public String getCookieLine() {
		return cookies.keySet().stream().map(s -> String.format("%s=%s", s, cookies.get(s)))
			.collect(Collectors.joining(";"));
	}

	public List<String> getHeaderLines() {
		List<String> headerLines = headers.keySet().stream().map(s -> String.format("%s: %s\r\n", s, headers.get(s)))
			.collect(Collectors.toList());
		String cookieLine = getCookieLine();
		if (cookieLine != null && !cookieLine.isEmpty()) {
			headerLines.add(String.format("%s: %s", "Set-Cookie", cookieLine));
		}
		return headerLines;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public void forward(String path) {
		try {
			File file = new File("./webapp" + path);

			if (!file.exists()) {
				// TODO: 2018. 4. 9.  파일 뿐만 아니라  String 리턴은 어떻게 할지 고민해보자
				this.setStatus(HttpStatusCode.NOT_FOUND);
			} else {
				this.addHeader("Content-Type", ContentType.findContentTypeByFilename(file.getName()).getMimeType());
				this.setStatus(HttpStatusCode.OK);
				this.setBody(Files.readAllBytes(file.toPath()));
			}
			int bodyLength = this.getBody() == null ? 0 : this.getBody().length;
			this.addHeader("Content-Length", String.valueOf(bodyLength));
			flushResponse();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void sendRedirect(String url) {
		try {
			this.setStatus(HttpStatusCode.FOUND);
			this.addHeader("Location", url);
			flushResponse();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void flushResponse() throws IOException {

		dos.writeBytes(this.status.getLine());
		for (String header : this.getHeaderLines()) {
			dos.writeBytes(header);
		}

		dos.writeBytes("\r\n");

		if (Objects.nonNull(this.getBody())) {
			dos.write(this.getBody(), 0, this.getBody().length);
		}
		dos.flush();

	}
}
