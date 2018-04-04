package webserver.controller;

import webserver.handler.*;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;
import webserver.http.HttpStatusCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

	public HttpResponse action(HttpRequest httpRequest) throws IOException {
		ActionHandler handler = handlerMap.get(httpRequest.getUrl().getRequestPath());
		HttpResponse httpResponse = new HttpResponse();
		httpResponse.setHeader("Content-Type", httpRequest.getHeader("Accept"));

		String viewPage = Objects.isNull(handler) ?
			httpRequest.getUrl().getRequestPath() : handler.process(httpRequest, httpResponse);

		// TODO: 2018. 4. 3. 리팩토링 필요
		int index = viewPage.lastIndexOf(REDIRECT_KEYWORD);
		if (index != -1) {
			// Redirect
			String url = viewPage.substring(index + REDIRECT_KEYWORD.length());
			httpResponse.setStatus(HttpStatusCode.FOUND);
			httpResponse.setHeader("Location", url);
		} else {
			// Forward
			httpResponse.setStatus(HttpStatusCode.OK);
			if (!viewPage.isEmpty()) {
				File file = new File("./webapp" + viewPage);
				if (!file.exists()) {
					httpResponse.setBody(viewPage.getBytes());
				} else {
					httpResponse.setBody(Files.readAllBytes(file.toPath()));
				}
				int bodyLength = httpResponse.getBody() == null ? 0 : httpResponse.getBody().length;
				httpResponse.setHeader("Content-Length", String.valueOf(bodyLength));
			}
		}
		return httpResponse;
	}
}
