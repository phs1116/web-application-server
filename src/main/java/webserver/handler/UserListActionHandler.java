package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

/**
 * Created by hspark on 2018. 4. 3..
 */
public class UserListActionHandler implements ActionHandler {
	public static final String URL = "/user/list";
	@Override
	public String process(HttpRequest httpRequest, HttpResponse httpResponse) {
		boolean isLogined = Boolean.parseBoolean(httpRequest.getCookie("logined"));
		if(isLogined){
			return "/user/list.html";
		}
		return "/user/login.html";
	}
}
