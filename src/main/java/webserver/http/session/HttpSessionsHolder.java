package webserver.http.session;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by hspark on 2018. 4. 18..
 */
public class HttpSessionsHolder {
	private static Map<String, HttpSession> sessions = Maps.newConcurrentMap();

	public static void setSession(String id, HttpSession session) {
		sessions.put(id, session);
	}

	public static HttpSession getSession(String id) {
		return sessions.computeIfAbsent(id, NextHttpSession::new);
	}

	public static void remove(String id) {
		sessions.remove(id);
	}
}
