package com.test.initDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

public class YamlUtil {
	String yamlfile = "D:\\tools\\eclipse_luna\\workspace\\appTester\\src\\test\\java\\com\\test\\initDriver\\capability.yaml";

	
	public static Map<String, Object> readYml(String file) {
		Yaml yml = new Yaml();
		return yml.load(getStreamFromFile(file));

	}

	private static InputStream getStreamFromFile(String file) {
		return Objects.requireNonNull(YamlUtil.class.getClassLoader()
				.getResourceAsStream(file));
	}

	@Test
	public void read(String yamlPath, String keywords) throws IOException {
		Yaml yml = new Yaml();
		FileReader reader = new FileReader(yamlPath);
		BufferedReader buffer = new BufferedReader(reader);
		Map<String, Object> map = yml.load(buffer);

		System.out.println(map.get(keywords));
		buffer.close();
		reader.close();
	}

	@Test
	public void write() throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("key1", 1);
		map.put("key2", "2");
		Yaml yml = new Yaml();
		FileWriter writer = new FileWriter("src\\test\\java\\test.yml", true);
		BufferedWriter buffer = new BufferedWriter(writer);
		buffer.newLine();
		yml.dump(map, buffer);
		buffer.close();
		writer.close();
	}

}
