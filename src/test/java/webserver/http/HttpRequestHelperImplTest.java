package webserver.http;

import org.junit.Test;
import webserver.http.request.HttpMethod;
import webserver.http.request.HttpRequest;
import webserver.http.request.HttpRequestHelperImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by hspark on 2018. 4. 9..
 */
public class HttpRequestHelperImplTest {
	private String testDirectory = "./src/test/resources/";

	@Test
	public void request_Get() throws Exception {
		InputStream in = new FileInputStream(new File(testDirectory + "Http_GET.txt"));
		HttpRequestHelperImpl httpRequestHelper = new HttpRequestHelperImpl();
		HttpRequest httpRequest = httpRequestHelper.create(in);

		assertEquals(HttpMethod.GET, httpRequest.getMethod());
		assertEquals("/user/create", httpRequest.getRequestPath());
		assertEquals("keep-alive", httpRequest.getHeader("Connection"));
		assertEquals("javajigi", httpRequest.getParameter("userId"));
	}

	@Test
	public void request_Post() throws Exception {
		InputStream in = new FileInputStream(new File(testDirectory + "Http_POST.txt"));
		HttpRequestHelperImpl httpRequestHelper = new HttpRequestHelperImpl();
		HttpRequest httpRequest = httpRequestHelper.create(in);

		assertEquals(HttpMethod.POST, httpRequest.getMethod());
		assertEquals("/user/create", httpRequest.getRequestPath());
		assertEquals("keep-alive", httpRequest.getHeader("Connection"));
		assertEquals("javajigi", httpRequest.getParameter("userId"));

	}
}