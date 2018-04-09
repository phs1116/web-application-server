package webserver.controller;

import webserver.handler.*;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class HttpController {
	public Map<String, ActionHandler> handlerMap = new HashMap<>();
	private static final String REDIRECT_KEYWORD = "redirect:";

	public HttpController() {
		handlerMap.put(UserCreateActionHandler.URL, new UserCreateActionHandler());
		handlerMap.put(DefaultActionHandler.URL, new DefaultActionHandler());
		handlerMap.put(LoginActionHandler.URL, new LoginActionHandler());
		handlerMap.put(UserListActionHandler.URL, new UserListActionHandler());
	}

	public void action(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
		ActionHandler handler = handlerMap.get(httpRequest.getRequestPath());
		httpResponse.addHeader("Content-Type", httpRequest.getHeader("Accept"));

		String viewPage = Objects.isNull(handler) ?
			httpRequest.getRequestPath() : handler.process(httpRequest, httpResponse);

		// TODO: 2018. 4. 3. 리팩토링 필요
		int index = viewPage.lastIndexOf(REDIRECT_KEYWORD);
		if (index != -1) {
			// Redirect
			httpResponse.sendRedirect(viewPage.substring(index + REDIRECT_KEYWORD.length()));
		} else {
			// Forward
			httpResponse.forward(viewPage);
		}
	}
}
