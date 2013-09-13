package cn.flysnowxf.str;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtils {
	public static String objectToXml(Class<?> clazz, Object object)
			throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(object, stringWriter);

		return stringWriter.toString();
	}

	public static Object xmlToObject(Class<?> clazz, String xml)
			throws Exception {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		return unmarshaller.unmarshal(toInputStream(xml, "utf-8"));
	}

	private static InputStream toInputStream(String input, String encoding)
			throws IOException {
		byte[] bytes = (encoding != null) ? input.getBytes(encoding) : input
				.getBytes();
		return new ByteArrayInputStream(bytes);
	}

	/**
	 * 对非法字符进行替换
	 * @param value
	 * @return
	 */
	public static String checkUnicodeString(String value) {
		char[] xmlChar = value.toCharArray();
		for (int i = 0; i < xmlChar.length; ++i) {
			if (xmlChar[i] > 0xFFFD) {
				xmlChar[i] = ' ';// 用空格替换
			} else if (xmlChar[i] < 0x20 && xmlChar[i] != 't'
					&& xmlChar[i] != 'n' && xmlChar[i] != 'r') {
				xmlChar[i] = ' ';// 用空格替换
			}
		}
		
		return new String(xmlChar);
	}
}
