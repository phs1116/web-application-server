package webserver.http.request;

import com.google.common.collect.Maps;
import util.HttpRequestUtils;
import webserver.http.cookie.HttpCookie;
import webserver.http.session.HttpSession;

import java.util.Map;

/**
 * Created by hspark on 2018. 3. 29..
 */
public class HttpRequest {
	private static final String EMPTY_QUERY_STRING = "";
	private String requestPath;
	private String queryString;
	private HttpMethod method;
	private String httpVersion; // TODO: 2018. 4. 1. 추후 필요하다면 enum으로
	private Map<String, String> headers = Maps.newHashMap();
	private Map<String, String> parameters = Maps.newHashMap();
	private HttpCookie httpCookie;
	private HttpSession httpSession;

	public static HttpRequest generateByRequestLine(String requestLine) {
		HttpRequest httpRequest = new HttpRequest();

		if (requestLine != null) {
			String[] tokens = requestLine.split(" ");
			httpRequest.setMethod(HttpMethod.valueOf(tokens[0]));
			httpRequest.setUrl(tokens[1]);
			httpRequest.setHttpVersion(tokens[2]);
			httpRequest.addParameters(HttpRequestUtils.parseQueryString(httpRequest.getQueryString()));
		}
		return httpRequest;
	}

	public void setUrl(String url) {
		int index = url.indexOf("?");
		if (index > -1) {
			this.requestPath = url.substring(0, index);
			this.queryString = url.substring(index + 1);
		} else {
			this.requestPath = url;
			this.queryString = EMPTY_QUERY_STRING;
		}
	}

	public String getRequestPath() {
		return requestPath;
	}

	public String getQueryString(){
		return queryString;
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

	public void addHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
	}

	public String getHeader(String key) {
		return headers.get(key);
	}

	public HttpCookie getCookies() {
		return this.httpCookie;
	}

	public void setHttpCookie(HttpCookie httpCookie){
		this.httpCookie = httpCookie;
	}
	public String getCookie(String key) {
		return this.httpCookie.getCookie(key);
	}

	public void setCookie(String key, String value) {
		this.httpCookie.setCookie(key, value);
	}

	public void addParameters(Map<String, String> bodyParams) {
		this.parameters.putAll(bodyParams);
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getParameter(String key) {
		return this.parameters.get(key);
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession session) {
		this.httpSession = session;
	}
}
