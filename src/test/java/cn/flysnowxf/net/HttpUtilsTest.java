package cn.flysnowxf.net;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class HttpUtilsTest {
	class TestResult {
		private boolean success;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}
	}
	
	@Test
	public void get() {
		String url = "http://g.cn";
		HttpRequest request = new HttpRequest(url);
		try {
			HttpResponse httpResponse = HttpUtils.get(request, 
						new AbstractResultHandler<TestResult>() {

					@Override
					protected Result handleResponse(String response) {
						return new Result(new TestResult(), true);
					}

					@Override
					protected void handleSuccess(TestResult r) {
						r.setSuccess(true);
					}

					@Override
					protected void handleFailure(TestResult r) {
						r.setSuccess(false);
					}

					@Override
					protected String getStatusLog() {
						return "source=g.cn";
					}

					@Override
					protected String getResultLog(TestResult r) {
						return "source=g.cn" + 
							"<|>code=" + r.isSuccess();
					}
			});
			Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void post() {
		String url = "http://g.cn";
		HttpRequest request = new HttpRequest(url);
		try {
			HttpResponse httpResponse = HttpUtils.post(request, null);
			Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
