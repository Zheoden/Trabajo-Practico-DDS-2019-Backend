package modelo.utils;

import java.io.InputStream;
import java.util.Scanner;

public class Utils {
    @SuppressWarnings("resource")
	public static String readFileFromResources(String path) {

		InputStream resourceStream = Utils.class.getClassLoader().getResourceAsStream(path);
		if (resourceStream == null) return "";

		return new Scanner(resourceStream, "UTF-8").useDelimiter("\\A").next();
    }
}
