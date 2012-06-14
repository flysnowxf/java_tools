package cn.flysnowxf.str;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.log4j.Logger;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

public class EmailUtils {
	private static final Logger logger = Logger.getLogger(EmailUtils.class);
	private static final String REGEX = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
	private static final String LOG_PREFIX = "[Check Email]";
	
	/**
	 * 最简单验证，验证基本格式
	 * @return
	 */
	public static boolean checkFormat(String email) {
		if(email == null || email.equals("")) {
			return false;
		}
		
		if(!email.matches(REGEX)) {
			logger.info(LOG_PREFIX + email + " format error!");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 快速使用nslookup验证邮箱域名，不返回域名结果
	 * @param email
	 * @return
	 */
	public static boolean speedCheckMx(String email) {
		if(!checkFormat(email)) {
			return false;
		}
		
		String hostname = email.split("@")[1];
		try {
			// 通过nslookup验证
			Lookup lookup = new Lookup(hostname, Type.MX);
			lookup.run();
			if(lookup.getResult() != Lookup.SUCCESSFUL) {
				logger.info(LOG_PREFIX + email + " mx error!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * 使用nslookup验证邮箱域名
	 * @param email
	 * @return 如果验证失败，返回null
	 */
	public static Record[] checkMx(String email) {
		if(checkFormat(email)) {
			String hostname = email.split("@")[1];
			
			try {
				// 通过nslookup验证
				Lookup lookup = new Lookup(hostname, Type.MX);
				lookup.run();
				if(lookup.getResult() != Lookup.SUCCESSFUL) {
					logger.info(LOG_PREFIX + email + " mx error!");
					return null;
				}
				
				return lookup.getAnswers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * 终极验证，使用smtp协议验证邮箱是否有真实存在
	 * 速度不理想，不建议验证大批量的数据
	 * @param email
	 * @return
	 */
	public static boolean checkUser(String email) {
		if(checkFormat(email)) {
			Record[] records = checkMx(email);
			if(records != null) {
				String hostname = email.split("@")[1];
				
				// 可能有多个服务器
				SMTPClient client = new SMTPClient();
				boolean isConnected = false;
				for (Record record : records) {
					try {
						String smtpHost = record.getAdditionalName().toString();
						client.connect(smtpHost);
						// 失败
						if(!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
							client.disconnect();
							continue;
						}
						// 成功连接上一个服务器
						else {
							isConnected = true;
							logger.info(LOG_PREFIX + "MX record about " + hostname + " exists.");
							logger.info(LOG_PREFIX + "Connection succeeded to " + smtpHost);
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(isConnected) {
					// 成功连接
					logger.info(LOG_PREFIX + client.getReplyString().replace("\r\n", ""));
					
					// smtp协议过程
					try {
						// helo
						client.login("verify");
						logger.info(LOG_PREFIX + ">HELO verify");
						logger.info(LOG_PREFIX + client.getReplyString().replace("\r\n", ""));
						if(SMTPReply.ACTION_OK != client.getReplyCode()) {
							return false;
						}
						
						// mail from
						// 以gmail邮箱为作为发送者
						client.setSender("verify@gmail.com");
						logger.info(LOG_PREFIX + ">MAIL FROM:<flysnowxf@gmail.com>");
						logger.info(LOG_PREFIX + client.getReplyString().replace("\r\n", ""));
						if(SMTPReply.ACTION_OK != client.getReplyCode()) {
							return false;
						}
						
						// rcpt to
						client.addRecipient(email);
						logger.info(LOG_PREFIX + ">RCPT TO:<" + email + ">");
						logger.info(LOG_PREFIX + client.getReplyString().replace("\r\n", ""));
						if(SMTPReply.ACTION_OK != client.getReplyCode()) {
							return false;
						}
						
						// 成功
						return true;
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							client.disconnect();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return false;
	}
}
