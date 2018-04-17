package webserver.http.cookie;

/**
 * Created by hspark on 2018. 4. 18..
 */
public interface HttpCookie {
	String getCookie(String key);
	void setCookie(String key, String value);
	String getSessionId();
}
