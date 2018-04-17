package webserver.http.request;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hspark on 2018. 4. 1..
 */
public interface HttpRequestHelper {
	HttpRequest create(InputStream inputStream) throws IOException;
}
