package webserver.http;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;
import webserver.RequestHandler;

import java.io.*;
import java.util.Map;

/**
 * Created by hspark on 2018. 4. 1..
 */
public class HttpRequestHelperImpl implements HttpRequestHelper {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

	@Override
	public HttpRequest create(BufferedReader bufferedReader) throws IOException {

		//Get Request Line
		String requestLine = bufferedReader.readLine();
		log.debug("Request Line : {}", requestLine);
		HttpRequest httpRequest = HttpRequest.generateByRequestLine(requestLine);

		//Get Header Line
		Map<String, String> headers = readHeaders(bufferedReader);
		httpRequest.setHeaders(headers);

		//Set Cookie
		httpRequest.setCookies(HttpRequestUtils.parseCookies(headers.get("Cookie")));

		//Get Body Line
		String contentLength = httpRequest.getHeaders().get("Content-Length");
		if (contentLength != null && !contentLength.isEmpty()) {
			Map<String, String> bodyParams = readBody(bufferedReader, httpRequest, contentLength);
			httpRequest.setBodyParams(bodyParams);
		}

		return httpRequest;
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
