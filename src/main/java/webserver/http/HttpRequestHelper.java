package webserver.http;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by hspark on 2018. 4. 1..
 */
public interface HttpRequestHelper {
	HttpRequest create(BufferedReader bufferedReader) throws IOException;
}
