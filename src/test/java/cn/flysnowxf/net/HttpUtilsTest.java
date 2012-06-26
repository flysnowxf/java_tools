package cn.flysnowxf.net;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class HttpUtilsTest {
	class Result {
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
		final Result result = new Result();
		try {
			HttpResponse httpResponse = HttpUtils.get(request, 
						new AbstractResultHandler<Result>() {

					@Override
					protected boolean isSuccess(String response) {
						return false;
					}

					@Override
					protected void handleSuccess() {
						result.setSuccess(true);
					}

					@Override
					protected void handleFailure() {
						result.setSuccess(false);
					}

					@Override
					protected String getStatusLog() {
						return "source=g.cn";
					}

					@Override
					protected String getSuccessLog() {
						return "source=g.cn";
					}

					@Override
					protected String getFailureLog() {
						return "source=g.cn";
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
