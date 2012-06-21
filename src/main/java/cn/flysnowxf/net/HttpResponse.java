package cn.flysnowxf.net;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpStatus;

public class HttpResponse {
	private int status = HttpStatus.SC_NOT_FOUND;
	private String response;
	private Header[] header;
	
	public Header[] getHeader() {
		return header;
	}
	public void setHeader(Header[] header) {
		this.header = header;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
