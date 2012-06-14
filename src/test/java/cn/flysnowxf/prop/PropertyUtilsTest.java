package cn.flysnowxf.prop;

public class PropertyUtilsTest {
	public static void main(String[] args) {
		System.out.println(PropertyUtils.getString("url"));
		System.out.println(PropertyUtils.getWeakString("test.properties", "name"));
	}
}
