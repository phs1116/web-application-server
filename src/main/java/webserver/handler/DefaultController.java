package webserver.handler;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class DefaultController extends AbstractController {
	public static final String URL = "/";

	@Override
	void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
	}

	@Override
	void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
		httpResponse.forward("/index.html");
	}
}