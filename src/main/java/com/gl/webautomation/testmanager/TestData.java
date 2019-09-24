package com.gl.webautomation.testmanager;

import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class TestData {
	
	 private HashMap<String, String> data = new HashMap<>();
	 
	 private String name;
	 
	
	public String getParamValue(String paramName)
    {
       return data.get(paramName.toUpperCase());
    }
	
	 public void setName(String name) {
	        this.name = name;
	 }
	 
	 public String getName() {
	        return name;
	    }
	 
	 public boolean setName(XMLEventReader eventReader) throws XMLStreamException {
	        XMLEvent event = eventReader.nextEvent();
	        if(event.getEventType() != XMLStreamConstants.CHARACTERS) {
	        	System.out.println("While processing TestDataRef, no data read from XML file.");
	            return false;
	        }

	        String value = event.asCharacters().getData();
	        System.out.println("Name: " + value);
	        this.name = value;
	        return true;

	   }
	 
	 public boolean setTestData(String name, XMLEventReader eventReader)
	            throws XMLStreamException {
	        XMLEvent event = eventReader.nextEvent();
	        if (event.getEventType() != XMLStreamConstants.CHARACTERS) {
	        	System.out.println("While processing Id, no data read from XML file.");
	            return false;
	        }

	        String value = event.asCharacters().getData();
	        data.put(name.toUpperCase(), value);
	        return true;
	    }
	 
	
	public static TestData xmlTestCaseHandler(XMLEventReader eventReader)
            throws XMLStreamException, IllegalArgumentException {
        TestData testData = new TestData();
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    System.out.println("Next XML Start Tag: " + startElement.getName().getLocalPart());
                    switch (startElement.getName().getLocalPart().toUpperCase()) {
                        case "TESTDATAREF":
                        	System.out.println("Processing Name");
                            if(!testData.setName(eventReader)) {
                            	System.out.println("Error while processing Name.");
                                throw new IllegalArgumentException("XML file is malformed");
                            }
                            break;
                        default:
                            if(!testData.setTestData(startElement.getName().getLocalPart(), eventReader)) {
                            	System.out.println("Error while processing: " + startElement.getName().getLocalPart() );
                                throw new IllegalArgumentException("XML file is malformed");
                            }
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    System.out.println("Next XML End Tag: " + endElement.getName().getLocalPart());
                    switch (endElement.getName().getLocalPart().toUpperCase()) {
                        case "TESTDATA":
                            return testData;
                        case "DATA":
                            continue;
                        default:
                            continue;
                    }
            }

        }
        throw new IllegalArgumentException("XML file is malformed");
    }


}
