package webserver.handler;

import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

/**
 * Created by hspark on 2018. 4. 1..
 */
public interface ActionHandler {
	String process(HttpRequest httpRequest, HttpResponse httpResponse);
}
