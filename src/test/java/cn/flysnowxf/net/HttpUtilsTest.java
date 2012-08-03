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
	
	class CallResult {
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
		final CallResult callResult = new CallResult();
		String url = "http://g.cn";
		HttpRequest request = new HttpRequest(url);
		HttpResponse httpResponse = HttpUtils.get(request, new AbstractResultHandler<TestResult>() {
			
				@Override
				protected Result handleResponse(String response) {
					return new Result(new TestResult(), true);
				}

				@Override
				protected void handleSuccess(TestResult r) {
					callResult.setSuccess(true);
				}

				@Override
				protected void handleFailure(TestResult r) {
					callResult.setSuccess(false);
				}

				@Override
				protected void handleStatusFailure() {
					callResult.setSuccess(false);
				}

				@Override
				protected void handleException(Exception e) {
					callResult.setSuccess(false);
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
		if(callResult.success) {
			Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatus());
		}
		
		// 不覆盖方法
		httpResponse = HttpUtils.get(request, new AbstractResultHandler<TestResult>() {

			@Override
			protected Result handleResponse(
					String response) {
				return new Result(new TestResult(), true);
			}
			
		});
	}
	
	@Test
	public void post() {
		String url = "http://g.cn";
		HttpRequest request = new HttpRequest(url);
		try {
			HttpResponse httpResponse = HttpUtils.post(request);
			Assert.assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, httpResponse.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
