package webserver.dispatcher;

import webserver.handler.*;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class HttpDispatcher {
	public Map<String, Controller> handlerMap = new HashMap<>();

	public HttpDispatcher() {
		handlerMap.put(UserCreateController.URL, new UserCreateController());
		handlerMap.put(DefaultController.URL, new DefaultController());
		handlerMap.put(LoginController.URL, new LoginController());
		handlerMap.put(UserListController.URL, new UserListController());
	}

	public void dispatch(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
		Controller handler = handlerMap.get(httpRequest.getRequestPath());
		if (Objects.nonNull(handler)) {
			handler.service(httpRequest, httpResponse);
		} else {
			httpResponse.forward(httpRequest.getRequestPath());
		}
	}
}
