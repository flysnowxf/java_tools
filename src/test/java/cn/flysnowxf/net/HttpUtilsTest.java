package cn.flysnowxf.net;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class HttpUtilsTest {
	@Test
	public void get() {
		String url = "http://g.cn";
		HttpRequest request = new HttpRequest(url);
		try {
			HttpResponse httpResponse = HttpUtils.get(request);
			Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void post() {
		String url = "";
		HttpRequest request = new HttpRequest(url);
		try {
			HttpResponse httpResponse = HttpUtils.post(request);
			Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
