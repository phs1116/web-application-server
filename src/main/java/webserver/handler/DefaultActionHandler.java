package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class DefaultActionHandler implements ActionHandler {
	public static final String URL = "/";
	@Override
	public String process(HttpRequest httpRequest, HttpResponse httpResponse) {
		return "Hello World";
	}
}
