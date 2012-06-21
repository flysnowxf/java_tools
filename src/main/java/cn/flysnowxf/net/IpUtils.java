package cn.flysnowxf.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpUtils {
	public static List<String> getLocalIp() {
		List<String> ipList = new ArrayList<String>();
		
		try {
			Enumeration<NetworkInterface> netInterfaces = 
				NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					InetAddress inet = ips.nextElement();
					// 排除环路和ipv6
					if(!inet.isLoopbackAddress()    
							&& inet.getHostAddress().indexOf(":") == -1) {
						ipList.add(inet.getHostAddress());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ipList;
	}
}
