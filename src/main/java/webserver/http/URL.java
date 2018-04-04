package webserver.http;

import util.HttpRequestUtils;

import java.util.Map;

/**
 * Created by hspark on 2018. 3. 29..
 */
public class URL {

	private String requestPath;
	private String queryString;
	private Map<String, String> params;

	public URL(String url) {
		int index = url.indexOf("?");
		if (index > -1) {
			this.requestPath = url.substring(0, index);
			this.queryString = url.substring(index + 1);
			this.params = HttpRequestUtils.parseQueryString(queryString);
		} else {
			this.requestPath = url;
		}
	}

	public String getRequestPath() {
		return requestPath;
	}

	public String getQueryString() {
		return queryString;
	}

	public Map<String, String> getParmas() {
		return params;
	}

}
