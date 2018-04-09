package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

/**
 * Created by hspark on 2018. 4. 3..
 */
public class UserListController extends AbstractController {
	public static final String URL = "/user/list";

	@Override
	void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
	}

	@Override
	void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
		boolean isLogined = Boolean.parseBoolean(httpRequest.getCookie("logined"));
		if (isLogined) {
			httpResponse.forward("/user/list.html");
		}
		httpResponse.sendRedirect("/user/login");
	}
}
