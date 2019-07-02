package modelo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;

import modelo.utils.Utils;

public class JsonParser {
	/**
	 * This global class abstract the use of Jackson directly in the project for
	 * Json parsing, with a default ObjectMapper configuration and checked to
	 * unchecked exception conversion
	 */
	private static final ObjectMapper objectMapper = (new ObjectMapper()).registerModule(new Jdk8Module())
			.registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true)
			.setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
			.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

	public <T> T read(String text, TypeReference<T> type) {
		try {
			return objectMapper.readValue(text, type);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void writeAsString(Object someObject, String file) {
		try {
			JsonGenerator generator = objectMapper.getFactory().createGenerator(new File(file), JsonEncoding.UTF8);
			objectMapper.writeValue(generator, someObject);
			generator.flush();
			generator.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public <T> T readFromResourceFile(String path, TypeReference<T> type) {
		String fileAsText = Utils.readFileFromResources(path);

		return read(fileAsText, type);
	}
}
