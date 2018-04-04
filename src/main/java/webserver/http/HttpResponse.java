package webserver.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class HttpResponse {
	private HttpStatusCode status;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String> cookies = new HashMap<>();
	private byte[] body;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public HttpStatusCode getStatus() {
		return status;
	}

	public void setStatus(HttpStatusCode status) {
		this.status = status;
	}

	public void setHeader(String key, String value) {
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
}
