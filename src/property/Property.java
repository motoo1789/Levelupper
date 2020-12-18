package property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	private static Property proObject = new Property();
	private static InputStream inputStream;
	private static  Properties properties;
	private static final String START_FILE_PATH = "properties/youser.properties";

	private Property() {

		try {

			properties = new Properties();
	        inputStream = Property.class.getClassLoader().getResourceAsStream(START_FILE_PATH);
            properties.load(inputStream);


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Property getInstance() {

		return proObject;

	}

	public String getProperty (final String key) {

		String str = properties.getProperty(key);
		return str;
	}

}
