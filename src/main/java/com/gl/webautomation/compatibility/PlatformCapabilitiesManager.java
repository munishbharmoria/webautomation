package com.gl.webautomation.compatibility;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.gl.webautomation.utils.PlatformCapabilityUtils;


public class PlatformCapabilitiesManager {

    private static PlatformCapabilitiesManager manager = new PlatformCapabilitiesManager();
    private List<PlatformCapability> platformCapabilityList = new LinkedList<>();

    /**
     * Gets manager for Platform Capabilities.
     *
     * @return the manager
     */
    public static PlatformCapabilitiesManager getManager()
    {
        return manager;
    }

    /**
     * initPlatformCapabilities boolean to define the Platform Capabilities.
     *
     * @return the boolean true if platform capabilities are added successfully, otherwise false if any error occurs
     */
    public static boolean initPlatformCapabilities() {

       // String capabilitiesFile = CommandLineParser.getCommandLineParser().getCapabilitiesInfo();
        ClassLoader classLoader;
        String capabilitiesFile = "";
		try {
			classLoader = Class.forName("com.gl.webautomation.compatibility.PlatformCapabilitiesManager").getClassLoader();
			capabilitiesFile = classLoader.getResource(PlatformCapabilityUtils.platformCapabilityFile).getFile();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
        if(capabilitiesFile.toString().isEmpty())
        {
            System.out.println("PlatformCapability List is not provided and hence setting up one platformCapability based on this system's properties");
            try {
                PlatformCapability platformCapability = new PlatformCapability();
                String osName = System.getProperty("os.name").toUpperCase();
                System.out.println("OS Name = " + osName);
                if (osName.contains("WINDOWS"))
                {
                    platformCapability.setPlatform("WINDOWS");
                    platformCapability.setBrowser("CHROME");
                }
                else if(osName.contains("MAC"))
                {
                    platformCapability.setPlatform("MAC");
                    platformCapability.setBrowser("SAFARI");
                }

                System.out.println("Adding PlatformCapability: " + platformCapability.toString() + " to platformCapability manager");
                manager.platformCapabilityList.add(platformCapability);
                return true;
            } catch (Exception e)
            {
                System.out.println(e);
                System.out.println("Error while processing capabilities. Please refer to previous errors for details.");
                return false;
            }
        }
        else
        {
            try {
                XMLInputFactory factory = XMLInputFactory.newInstance();
                XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(capabilitiesFile.toString()));
                PlatformCapability platformCapability = null;

                while (eventReader.hasNext()) {
                    XMLEvent event = eventReader.nextEvent();
                    switch (event.getEventType()) {
                        case XMLStreamConstants.START_ELEMENT:
                            StartElement startElement = event.asStartElement();
                            System.out.println("Next XML Start Tag: " + startElement.getName().getLocalPart());
                            switch (startElement.getName().getLocalPart().toUpperCase()) {
                                case "CAPABILITY":
                                    System.out.println("Processing Test Capability");
                                    platformCapability = PlatformCapability.xmlTestCaseHandler(eventReader);
                                    PlatformCapabilitiesManager.getManager().platformCapabilityList.add(platformCapability);
                                    break;
                                default:
                                    System.out.println("No specific handling implemented for tag: " + startElement.getName().getLocalPart());
                                    continue;
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            EndElement endElement = event.asEndElement();
                            System.out.println("Next XML End Tag: "+ endElement.getName().getLocalPart());
                            switch (endElement.getName().getLocalPart().toUpperCase())
                            {
                                default:
                                    System.out.println("No specific handling implemented for tag: " + endElement.getName().getLocalPart());
                                    continue;
                            }
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.out.println("Error while processing capabilities. Please refer to previous errors for details.");
                return false;
            }
        }
        return true;
    }

    /**
     * Gets platform capability list.
     *
     * @return the platform capability list
     */
    public List<PlatformCapability> getPlatformCapabilityList()
    {
        return platformCapabilityList;
    }

}
