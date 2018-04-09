package webserver.handler;

import dto.UserDTO;
import model.User;
import service.UserService;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.util.Optional;

/**
 * Created by hspark on 2018. 4. 2..
 */
public class LoginController extends AbstractController {
	public static final String URL = "/user/login";

	@Override
	void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
		addUser(httpRequest, httpResponse);
	}

	@Override
	void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
		addUser(httpRequest, httpResponse);
	}

	public void addUser(HttpRequest httpRequest, HttpResponse httpResponse) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(httpRequest.getParameters().get("userId"));
		userDTO.setPassword(httpRequest.getParameters().get("password"));
		Optional<User> userOptional = UserService.INSTANCE.findById(userDTO);

		if (userOptional.isPresent()) {
			httpResponse.getCookies().put("logined", "true");
			httpResponse.sendRedirect("/index.html");
		}
		httpResponse.getCookies().put("logined", "false");
		httpResponse.sendRedirect("/user/login_failed.html");
	}
}
