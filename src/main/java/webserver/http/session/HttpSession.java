package webserver.http.session;

/**
 * Created by hspark on 2018. 4. 18..
 */
public interface HttpSession {
	String getId();

	void setAttribute(String name, Object value);

	Object getAttribute(String name);

	void removeAttribute(String name);

	void invalidate();
}
