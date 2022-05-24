package com.ilabquality.modules.testing.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import com.ilabquality.modules.testing.BaseTest;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

class Configuration extends BaseTest {
  private static String sFilePath;
  private static Map<String, String> mapEnvironment;

  public Configuration(String FileName) {
		sFilePath = FileName;
	}

	public Map<String,String> getConfiguration(String className,String sheetName) {
		try {
			String content = Files.toString(new File(sFilePath), Charsets.UTF_8);

			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createJsonParser(content);

      JsonNode node = mapper.readTree(parser);
      int testCount = 0;
      ArrayNode arrNode = (ArrayNode) node.get("tests");
			mapEnvironment = new HashMap<>();

			if (arrNode.isArray()) {

				for (final JsonNode objNode : arrNode) {
          String testName = objNode.findValue("TestName").toString().replace("\"", "");
          String testDataPath = objNode.findValue("TestDataPath").toString().replace("\"", "");
          String testEnvironment = objNode.findValue("TestEnvironment").toString().replace("\"", "");
          String testStatus = objNode.findValue("Status").toString().replace("\"", "");
          String testURL = objNode.findValue("URL").toString().replace("\"", "");
          String testResultFolder = objNode.findValue("TestResults").toString().replace("\"", "");
          String executionSheet = objNode.findValue("ExecutionSheet").toString().replace("\"", "");
          String fileLocation = objNode.findValue("FileLocation").toString().replace("\"", "");

					if ((testName.equals(className) && (testStatus.equals("Enabled")))) {
						mapEnvironment.put("testName", testName);
						mapEnvironment.put("testDataPath", testDataPath);
						mapEnvironment.put("testEnvironment", testEnvironment);
						mapEnvironment.put("testStatus", testStatus);
						mapEnvironment.put("testURL", testURL);
						mapEnvironment.put("testResults", testResultFolder);
						mapEnvironment.put("executionSheet", executionSheet);
						mapEnvironment.put("fileLocation", fileLocation);
						testCount++;
						break;
					}
				}

				if (testCount > 1) {
					System.out.println("Only one environment can be executed at a time for test '"
            + className.trim().toUpperCase()
            + "'. Please update the config.json file accordingly");
					System.out.println("The test was terminated");

				} else if (testCount == 0) {
					System.out.println("Either the configuration is Disabled or there is no entry in the configuration file for '"
            + className.trim().toUpperCase()
            + "' test");
					System.out.println("The test was terminated");
				}
			}

		} catch (FileNotFoundException exception) {
			System.out.println("The configuration file is not found. Make sure the path is correct");
      exception.printStackTrace();

		} catch (IOException exception) {
      exception.printStackTrace();
		}

		return mapEnvironment;
	}
}



