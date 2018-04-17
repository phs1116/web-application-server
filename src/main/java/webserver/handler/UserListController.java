package webserver.handler;

import model.User;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

import java.util.Objects;

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
		User loginUser = (User)httpRequest.getHttpSession().getAttribute("user");
		if (Objects.nonNull(loginUser)) {
			httpResponse.forward("/user/list.html");
			return;
		}
		httpResponse.sendRedirect("/user/login");
	}
}
