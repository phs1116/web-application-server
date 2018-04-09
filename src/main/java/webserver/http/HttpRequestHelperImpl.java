package webserver.http;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class HttpRequestHelperImpl implements HttpRequestHelper {
	private static final Logger log = LoggerFactory.getLogger(HttpRequestHelper.class);

	@Override
	public HttpRequest create(InputStream inputStream) throws IOException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			//Get Request Line
			String requestLine = bufferedReader.readLine();
			log.debug("Request Line : {}", requestLine);
			HttpRequest httpRequest = HttpRequest.generateByRequestLine(requestLine);

			//Get Header Line
			Map<String, String> headers = readHeaders(bufferedReader);
			httpRequest.addHeaders(headers);

			//Set Cookie
			httpRequest.addCookies(HttpRequestUtils.parseCookies(headers.get("Cookie")));

			//Get Body Line
			String contentLength = httpRequest.getHeaders().get("Content-Length");
			if (contentLength != null && !contentLength.isEmpty()) {
				Map<String, String> bodyParams = readBody(bufferedReader, httpRequest, contentLength);
				httpRequest.addParameters(bodyParams);
			}
			return httpRequest;
		} catch (IOException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	private Map<String, String> readBody(BufferedReader bufferedReader, HttpRequest httpRequest, String contentLength) throws IOException {
		String bodyString = IOUtils.readData(bufferedReader, Integer.parseInt(contentLength));
		log.debug("body : {}", bodyString);
		return HttpRequestUtils.parseQueryString(bodyString);
	}

	private Map<String, String> readHeaders(BufferedReader bufferedReader) throws IOException {
		Map<String, String> header = Maps.newHashMap();
		for (String headerLine = bufferedReader.readLine(); headerLine != null && !headerLine.trim().isEmpty();
			 headerLine = bufferedReader.readLine()) {
			HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(headerLine);
			header.put(pair.getKey(), pair.getValue());
			log.debug("Header Field, key : {}, value : {}", pair.getKey(), pair.getValue());
		}
		return header;
	}
}
