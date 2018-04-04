package webserver.http;

import java.util.Map;

/**
 * Created by hspark on 2018. 3. 29..
 */
public class HttpRequest {
	private HttpMethod method;
	private URL url;
	private String httpVersion; // TODO: 2018. 4. 1. 추후 필요하다면 enum으로
	private Map<String, String> headers;
	private Map<String, String> cookies;

	private Map<String, String> bodyParams;

	public Map<String, String> getBodyParams() {
		return bodyParams;
	}

	public void setBodyParams(Map<String, String> bodyParams) {
		this.bodyParams = bodyParams;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public static HttpRequest generateByRequestLine(String requestLine) {
		HttpRequest httpRequest = new HttpRequest();

		if (requestLine != null) {
			String[] tokens = requestLine.split(" ");
			httpRequest.setMethod(HttpMethod.valueOf(tokens[0]));
			httpRequest.setUrl(new URL(tokens[1]));
			httpRequest.setHttpVersion(tokens[2]);
		}
		return httpRequest;
	}

	public String getHeader(String key){
		if(headers == null){
			return null;
		}
		return headers.get(key);
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public String getCookie(String key){
		return this.cookies.get(key);
	}

	public void setCookie(String key, String value){
		this.cookies.put(key, value);
	}
}
