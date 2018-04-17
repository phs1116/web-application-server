package webserver.handler;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

/**
 * Created by hspark on 2018. 4. 1..
 */
public interface Controller {
	void service(HttpRequest httpRequest, HttpResponse httpResponse);
}
