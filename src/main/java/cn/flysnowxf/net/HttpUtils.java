package cn.flysnowxf.net;


import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtils {
	private static HttpClient getClient(HttpRequest httpRequest) {
		HttpClient httpClient = new HttpClient();
		HttpConnectionManagerParams params = httpClient.getHttpConnectionManager().getParams();
		// timeout
		params.setSoTimeout(httpRequest.getSoTimeout());
		params.setConnectionTimeout(httpRequest.getConnTimeout());
		
		return httpClient;
	}
	
	private static void packageMethod(HttpMethod method, HttpRequest httpRequest) {
		if(httpRequest.getEncoding() != null) {
			HttpMethodParams params = method.getParams();
			params.setHttpElementCharset(httpRequest.getEncoding());
			params.setCredentialCharset(httpRequest.getEncoding());
			params.setContentCharset(httpRequest.getEncoding());
		}
		method.addRequestHeader("User-Agent", httpRequest.getUa());
		if (httpRequest.getHeader() != null) {
			for (Header header : httpRequest.getHeader()) {
				method.addRequestHeader(header);
			}
		}
	}
	
	private static <T> HttpResponse execute(HttpMethod method, HttpRequest httpRequest, AbstractResultHandler<T> handler) {
		HttpResponse httpResponse = new HttpResponse();
		
		HttpClient httpClient = getClient(httpRequest);
		try {
			int status = httpClient.executeMethod(method);
			String response = new String(method.getResponseBody(), httpRequest.getResponseEncoding());
			
			httpResponse.setStatus(status);
			httpResponse.setHeader(method.getResponseHeaders());
			httpResponse.setResponse(response);
			
			// 处理结果
			if(handler != null) {
				handler.handle(status, httpRequest.getUrl(), 
						response, httpRequest.isLog());
			}
		} catch (Exception e) {
			if(handler != null) {
				handler.exception(e, httpRequest.getUrl(), httpRequest.isLog());
			}
		} finally {
			method.releaseConnection();
			((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
		}
		
		return httpResponse;
	}
	
	public static <T> HttpResponse get(HttpRequest httpRequest, AbstractResultHandler<T> handler) {
		GetMethod method = new GetMethod(httpRequest.getUrl());
		packageMethod(method, httpRequest);
		return execute(method, httpRequest, handler);
	}
	
	public static HttpResponse get(HttpRequest httpRequest) {
		return get(httpRequest, null);
	}

	public static <T> HttpResponse post(HttpRequest httpRequest, AbstractResultHandler<T> handler) {
		PostMethod method = new PostMethod(httpRequest.getUrl());
		packageMethod(method, httpRequest);
		// 普通参数
		if(httpRequest.getData() != null) {
			method.setRequestBody(httpRequest.getData());
		}
		// 多媒体参数
		else if(httpRequest.getPart() != null) {
			method.setRequestEntity(new MultipartRequestEntity(httpRequest.getPart(), method.getParams()));
		}
		// 内容参数，默认为xml内容
		else if(httpRequest.getContent() != null) {
			try {
				method.setRequestEntity(
						new StringRequestEntity(httpRequest.getContent(), 
								httpRequest.getContentType(), httpRequest.getEncoding()));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return execute(method, httpRequest, handler);
	}
	
	public static HttpResponse post(HttpRequest httpRequest) {
		return post(httpRequest, null);
	}
}
