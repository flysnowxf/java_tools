package cn.flysnowxf.lang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ClassPathResource {
	private final String path;
	private ClassLoader classLoader;
	
	public ClassPathResource(String path) {
		this.path = path;
		this.classLoader = Thread.currentThread().getContextClassLoader();
	}
	
	public InputStream getInputStream() throws IOException {
		InputStream is = this.classLoader.getResourceAsStream(this.path);
		if (is == null) {
			throw new FileNotFoundException(
					getDescription() + " cannot be opened because it does not exist");
		}
		return is;
	}
	
	public URL getInputPath() {
		return this.classLoader.getResource(this.path);
	}
	
	public String getDescription() {
		return "class path resource [" + this.path + "]";
	}
}
