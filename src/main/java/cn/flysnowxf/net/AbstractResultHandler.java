package cn.flysnowxf.net;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;

public abstract class AbstractResultHandler<T> {
	public static final SimpleDateFormat BASIC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Logger log = Logger.getLogger(AbstractResultHandler.class);
	
	protected abstract boolean isSuccess(String response);
	protected abstract void handleSuccess();
	protected abstract void handleFailure();
	protected abstract String getStatusLog();
	protected abstract String getSuccessLog();
	protected abstract String getFailureLog();

	public void handle(int status, String url, String response, boolean isLog) {
		if(status == HttpStatus.SC_OK) {
			if(isSuccess(response)) {
				if(isLog) {
					log.info("at=" + BASIC.format(new Date()) +
							"<|>url=" + url +
							"<|>success=true" +
							"<|>" + getSuccessLog());
				}
				handleSuccess();
			}
			else {
				if(isLog) {
					log.error("at=" + BASIC.format(new Date()) +
							"<|>url=" + url +
							"<|>success=false" +
							"<|>" + getFailureLog());
				}
				handleFailure();
			}
		}
		else {
			if(isLog) {
				log.error("at=" + BASIC.format(new Date()) +
						"<|>url=" + url + 
						"<|>status=" + status +
						"<|>" + getStatusLog());
			}
		}
	}

}
