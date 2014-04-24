package cn.flysnowxf.map;

import java.text.MessageFormat;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpStatus;

import cn.flysnowxf.net.HttpRequest;
import cn.flysnowxf.net.HttpResponse;
import cn.flysnowxf.net.HttpUtils;

import com.google.gson.Gson;

public class GpsToBaidu {
	private static String URL = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x={0}&y={1}";

	public static String convert(String lng, String lat) {
		String url = MessageFormat.format(URL, lng, lat);
		
		HttpRequest request = new HttpRequest(url);
		HttpResponse httpResponse = HttpUtils.get(request);
		
		if(httpResponse.getStatus() == HttpStatus.SC_OK) {
			XY xy = new Gson().fromJson(httpResponse.getResponse(), XY.class);
			if(xy.getError() == 0) {
				// base64解码
				String lng2 = deconde(xy.getX());
				String lat2 = deconde(xy.getY());
				output(lng, lat, lng2, lat2);
				
				return lng2 + "," + lat2;
			}
		}
		
		return null;
	}

	private static String deconde(String txt) {
		byte[] b = txt.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		
		return s;
	}

	private static void output(String lng, String lat, String lng2, String lat2) {
		String message = "GPS经度纬度{0} -> 百度经度纬度{1}";
		System.out.println(MessageFormat.format(message, lng + "," + lat, lng2
				+ "," + lat2));
	}
}
