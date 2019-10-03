package com.gl.webautomation.testmanager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.gl.webautomation.utils.WebAutomationUtils;

public class TestManager {

	private static int includeLevel = 0;

	private HashMap<String, TestData> testDataHashMap = new HashMap<String, TestData>();

	private static TestManager manager = new TestManager();

	public static TestManager getTestManager() {
		return manager;
	}

	public TestData getTestData(String name) {
		return testDataHashMap.get(name);
	}

	public static void initTestData() {

		try {
			ClassLoader classLoader = Class.forName("com.gl.webautomation.testmanager.TestManager").getClassLoader();
			URL url = classLoader.getResource(WebAutomationUtils.dataFile);
			parseXML(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void parseXML(URL fileName) {

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader eventReader;
		try {
			eventReader = factory.createXMLEventReader(new FileReader(fileName.getFile()));

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				switch (event.getEventType()) {

				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					System.out.println("Next XML Start Tag: " + startElement.getName().getLocalPart());
					switch (startElement.getName().getLocalPart().toUpperCase()) {
					case "TESTDATA":
						System.out.println("Processing Test Data");
						TestData testData = TestData.xmlTestCaseHandler(eventReader);
						TestManager.getTestManager().testDataHashMap.put(testData.getName(), testData);
						break;
					default:
						System.out.println(
								"No specific handling implemented for tag: " + startElement.getName().getLocalPart());
						continue;
					}
					break;

				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					System.out.println("Next XML End Tag: " + endElement.getName().getLocalPart());
					switch (endElement.getName().getLocalPart().toUpperCase()) {
					case "INCLUDE":
						System.out.println("Processing Include End Tag");
						break;
					default:
						System.out.println(
								"No specific handling implemented for tag: " + endElement.getName().getLocalPart());
						continue;
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

}
