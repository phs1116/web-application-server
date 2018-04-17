package webserver.http.response;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hspark on 2018. 4. 9..
 */
public enum ContentType {
	HTML(Arrays.asList(".html"), "text/html;charset=utf-8"),
	CSS(Arrays.asList(".css"), "text/css"),
	JAVASCRIPT(Arrays.asList(".js"), "application/javascript");

	private List<String> extensionList;
	private String mimeType;

	ContentType(List<String> extensionList, String mimeType) {
		this.extensionList = extensionList;
		this.mimeType = mimeType;
	}

	public static ContentType findContentTypeByFilename(String filename) {
		int extensionIndex = filename.lastIndexOf(".");
		if (extensionIndex != -1) {
			String extension = filename.substring(extensionIndex);
			return Arrays.stream(ContentType.values())
				.filter(contentType -> contentType.extensionList.contains(extension))
				.findAny().orElse(HTML);
		}
		return HTML;
	}

	public List<String> getExtensionList() {
		return extensionList;
	}

	public String getMimeType() {
		return mimeType;
	}
}
