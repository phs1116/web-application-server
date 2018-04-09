package webserver.handler;

import webserver.http.HttpMethod;
import webserver.http.HttpRequest;
import webserver.http.HttpResponse;

/**
 * Created by hspark on 2018. 4. 9..
 */
abstract public class AbstractController implements Controller{
	@Override
	public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
		if(httpRequest.getMethod() == HttpMethod.POST){
			doPost(httpRequest, httpResponse);
		}else if(httpRequest.getMethod() == HttpMethod.GET){
			doGet(httpRequest, httpResponse);
		}
	}

	abstract void doPost(HttpRequest httpRequest, HttpResponse httpResponse);

	abstract void doGet(HttpRequest httpRequest, HttpResponse httpResponse);
}
