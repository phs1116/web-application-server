package webserver.handler;

import model.User;
import service.UserService;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.util.Map;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class UserCreateActionHandler implements ActionHandler {
	public static final String URL = "/user/create";

	@Override
	public String process(HttpRequest httpRequest, HttpResponse httpResponse) {
		Map<String, String> params = httpRequest.getParameters();
		User user = new User();
		user.setEmail(params.get("email"));
		user.setName(params.get("name"));
		user.setPassword(params.get("password"));
		user.setUserId(params.get("userId"));
		UserService.INSTANCE.addUser(user);
		return "redirect:/index.html";
	}
}
