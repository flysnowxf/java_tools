package cn.flysnowxf.net;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.multipart.Part;

public class HttpRequest {
	private String url;
	private Header[] header;
	// for post
	private NameValuePair[] data;
	private Part[] part;
	// for xml content
	private String content;
	private String contentType = "application/xml";
	// encoding
	private String encoding = "utf-8";
	private String responseEncoding = "utf-8";
	// timeout
	private int connTimeout = 1000;
	private int soTimeout = 5000;
	// ua
	private String ua = "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.162 Safari/535.19";
	
	public HttpRequest(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Header[] getHeader() {
		return header;
	}
	public void setHeader(Header[] header) {
		this.header = header;
	}
	public NameValuePair[] getData() {
		return data;
	}
	public void setData(NameValuePair[] data) {
		this.data = data;
	}
	public Part[] getPart() {
		return part;
	}
	public void setPart(Part[] part) {
		this.part = part;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public int getConnTimeout() {
		return connTimeout;
	}
	public void setConnTimeout(int connTimeout) {
		this.connTimeout = connTimeout;
	}
	public int getSoTimeout() {
		return soTimeout;
	}
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	public String getResponseEncoding() {
		return responseEncoding;
	}
	public void setResponseEncoding(String responseEncoding) {
		this.responseEncoding = responseEncoding;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
}
