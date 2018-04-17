package webserver.http.cookie;

import util.HttpRequestUtils;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by hspark on 2018. 4. 18..
 */
public class NextHttpCookie implements HttpCookie {
	private Map<String, String> cookies;

	public NextHttpCookie(String cookieHeaderStr) {
		this.cookies = HttpRequestUtils.parseCookies(cookieHeaderStr);
	}

	@Override
	public String getCookie(String key) {
		return this.cookies.get(key);
	}

	@Override
	public void setCookie(String key, String value) {
		this.cookies.put(key, value);
	}

	@Override
	public String getSessionId() {
		String sessionId = getCookie("JSESSIONID");

		if (Objects.isNull(sessionId)) {
			sessionId = UUID.randomUUID().toString();
			setCookie("JSESSIONID", sessionId);
		}

		return sessionId;
	}
}
