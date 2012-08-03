package cn.flysnowxf.net;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;

public abstract class AbstractResultHandler<T> {
	public static final SimpleDateFormat BASIC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger log = Logger.getLogger(AbstractResultHandler.class);
	
	/**
	 * 处理响应结果方法
	 * @param response
	 * @return
	 */
	protected abstract Result handleResponse(String response);
	/**
	 * 以下方法默认不实现内容，需要实现覆盖即可
	 */
	protected void handleSuccess(T t) {
	}
	protected void handleFailure(T t) {
	}
	protected void handleStatusFailure() {
	}
	protected void handleException(Exception e) {
	}
	protected String getStatusLog() {
		return "";
	}
	protected String getResultLog(T t) {
		return "";
	}

	public void handle(int status, String url, String response, boolean isLog) {
		if(status == HttpStatus.SC_OK) {
			Result result = handleResponse(response);
			if(result != null) {
				T t = result.getT();
				
				if(result.isSuccess()){
					handleSuccess(t);
					if(isLog) {
						log.info("at=" + BASIC.format(new Date()) +
								"<|>url=" + url +
								"<|>success=true" +
								"<|>" + getResultLog(t));
					}
				}
				else {
					handleFailure(t);
					if(isLog) {
						log.error("at=" + BASIC.format(new Date()) +
								"<|>url=" + url +
								"<|>success=false" +
								"<|>" + getResultLog(t));
					}
				}
			}
		}
		else {
			handleStatusFailure();
			if(isLog) {
				log.error("at=" + BASIC.format(new Date()) +
						"<|>url=" + url + 
						"<|>status=" + status +
						"<|>" + getStatusLog());
			}
		}
	}
	
	public void exception(Exception e, String url, boolean isLog) {
		handleException(e);
		if(isLog) {
			log.error("at=" + BASIC.format(new Date()) +
					"<|>url=" + url + 
					"<|>exception=" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public class Result {
		private T t;
		private boolean isSuccess;
		
		public Result(T t, boolean isSuccess) {
			this.t = t;
			this.isSuccess = isSuccess;
		}
		
		public T getT() {
			return t;
		}
		public void setT(T t) {
			this.t = t;
		}
		public boolean isSuccess() {
			return isSuccess;
		}
		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}
	}

}
