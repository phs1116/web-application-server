package webserver.http.session;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by hspark on 2018. 4. 18..
 */
public class NextHttpSession implements HttpSession {
	private String sessionId;
	private Map<String, Object> attributes;

	public NextHttpSession(String sessionId) {
		this.sessionId = sessionId;
		this.attributes = Maps.newHashMap();
	}

	@Override
	public String getId() {
		return sessionId;
	}

	@Override
	public void setAttribute(String key, Object value) {
		this.attributes.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}

	@Override
	public void removeAttribute(String key) {
		this.attributes.remove(key);
	}

	@Override
	public void invalidate() {
		this.attributes.clear();
		HttpSessionsHolder.remove(this.sessionId);
	}
}
