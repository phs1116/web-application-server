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
public class LoginActionHandler implements ActionHandler {
	public static final String URL =  "/user/login";
	@Override
	public String process(HttpRequest httpRequest, HttpResponse httpResponse) {
			UserDTO userDTO = new UserDTO();
		userDTO.setUserId(httpRequest.getParameters().get("userId"));
		userDTO.setPassword(httpRequest.getParameters().get("password"));
		Optional<User> userOptional = UserService.INSTANCE.findById(userDTO);
		if(userOptional.isPresent()){
			httpResponse.getCookies().put("logined", "true");
			return "redirect:/index.html";
		}
		httpResponse.getCookies().put("logined", "false");
		return "/user/login_failed.html";
	}
}
