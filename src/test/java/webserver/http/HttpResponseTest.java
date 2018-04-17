package webserver.http;

import org.junit.Test;
import webserver.http.response.HttpResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by hspark on 2018. 4. 9..
 */
public class HttpResponseTest {
	private String testDirectory = "./src/test/resources/";

	@Test
	public void responseForward() throws Exception {
		HttpResponse httpResponse = new HttpResponse(createOutpuStream("Http_Forward.txt"));
		httpResponse.forward("/index.html");
	}

	@Test
	public void responseRedirect() throws Exception {
		HttpResponse httpResponse = new HttpResponse(createOutpuStream("Http_Redirect.txt"));
		httpResponse.sendRedirect("/index.html");
	}

	@Test
	public void responseCookies() throws Exception {
		HttpResponse httpResponse = new HttpResponse(createOutpuStream("Http_Cookie.txt"));
		httpResponse.addHeader("Set-Cookie", "logined=true");
		httpResponse.sendRedirect("/index.html");
	}

	private OutputStream createOutpuStream(String filename) throws FileNotFoundException {
		return new FileOutputStream(new File(testDirectory + filename));
	}

}