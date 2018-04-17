package webserver.http.response;

/**
 * Created by coupang on 2018. 4. 2..
 */
public enum HttpStatusCode {
	OK("200", "OK", "HTTP/1.1 200 OK \r\n"),
	FOUND("302", "Found", "HTTP/1.1 302 Found \r\n"),
	NOT_FOUND("404", "Not Found", "HTTP/1.1 404 Not Found \r\n");
	private String code;
	private String status;
	private String line;

	HttpStatusCode(String code, String status, String line) {
		this.code = code;
		this.status = status;
		this.line = line;
	}

	public String getLine() {
		return line;
	}
}
