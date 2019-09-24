package com.gl.webautomation.compatibility;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.openqa.selenium.Platform;


public class PlatformCapability {

    private Platform platform;
    private String browser;
    private String version;
    private int instances;


    public String getStringForReport()
    {
        String s = platform+"|"+browser;
        if(!version.isEmpty())
             s += "|"+version;
        return s;
    }



    /**
     * Instantiates a new Platform capability like platform, browser, version, instances
     */
    public PlatformCapability() throws IOException, URISyntaxException {
        platform = Platform.ANY;
        browser = "";
        version = "";
        instances = 0;
    }

    /**
     * Sets browser through XML.
     *
     * @param eventReader the event reader of XML for browser being passed
     * @return true if browser value is set, otherwise false
     * @throws XMLStreamException the xml stream exception
     */
    public boolean setBrowser(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event = eventReader.nextEvent();
        if(event.getEventType() != XMLStreamConstants.CHARACTERS) {
            System.out.println("While processing Browser, no data read from XML file.");
            return false;
        }

        String value = event.asCharacters().getData();
        System.out.println("Browser: " + value);
        this.browser = value;
        return true;

    }

    /**
     * Sets browser through String.
     *
     * @param browser to pass the browser to be used
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * Gets browser.
     *
     * @return the browser
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Sets instances through XML.
     *
     * @param eventReader the event reader of XML for instances being passed
     * @return true if instances value is set, otherwise false
     * @throws XMLStreamException the xml stream exception
     */
    public boolean setInstances(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event = eventReader.nextEvent();
        if(event.getEventType() != XMLStreamConstants.CHARACTERS) {
            System.out.println("While processing Instances, no data read from XML file.");
            return false;
        }

        String value = event.asCharacters().getData();
        System.out.println("Instances: " + value);
        this.instances = Integer.valueOf(value);
        return true;

    }

    /**
     * Sets instances through String.
     *
     * @param instances to pass the instances to be used
     */
    public void setInstances(int instances) {
        this.instances = instances;
    }

    /**
     * Gets instances.
     *
     * @return the instances
     */
    public int getInstances() {
        return instances;
    }

    /**
     * Sets platform through XML.
     *
     * @param eventReader the event reader of XML for platform being passed
     * @return true if platform value is set, otherwise false
     * @throws XMLStreamException the xml stream exception
     */
    public boolean setPlatform(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event = eventReader.nextEvent();
        if(event.getEventType() != XMLStreamConstants.CHARACTERS) {
            System.out.println("While processing Platform, no data read from XML file.");
            return false;
        }

        String value = event.asCharacters().getData();
        System.out.println("Platform: " + value);
        setPlatform(value);
        return true;

    }

    /**
     * Sets platform through String.
     *
     * @param platform to pass the platform to be used
     */
    public void setPlatform(String platform) {
        switch (platform.toUpperCase())
        {
            case "WINDOWS":
                this.platform = Platform.WINDOWS;
                break;
            case "MAC":
                this.platform = Platform.MAC;
                break;
            case "ANDROID":
                this.platform = Platform.ANDROID;
                break;
            case "IOS":
                this.platform = Platform.ANY;  // Need to update correct value for IOS
                break;
        }
    }

    /**
     * Gets platform.
     *
     * @return the platform
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * Sets version through XML.
     *
     * @param eventReader the event reader of XML for version being passed
     * @return true if version value is set, otherwise false
     * @throws XMLStreamException the xml stream exception
     */
    public boolean setVersion(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event = eventReader.nextEvent();
        if(event.getEventType() != XMLStreamConstants.CHARACTERS) {
            System.out.println("While processing Version, no data read from XML file.");
            return false;
        }

        String value = event.asCharacters().getData();
        System.out.println("Version: " + value);
        setPlatform(value);
        return true;

    }

    /**
     * Sets version through String.
     *
     * @param version to pass the version to be used
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Validates if platform is available.
     *
     * @return the boolean true if platform is available, otherwise false
     */
    public boolean validate()
    {
        if(platform == Platform.ANY)
            return false;
        return true;
    }

    /**
     * Converts the platform capabilities to toString.
     *
     * @return the platform capabilities such as platform, browser, version, instances
     */
    @Override
    public String toString()
    {
        return "PlatformCapability [Platform = " + platform + ", Browser = "
                + browser + ", Version = " + version + " Instances = " + instances + "]";
    }

    /**
     * Xml test case handler platform capability.
     *
     * @param eventReader the event reader for test cases to be handled
     * @return the platform capability
     * @throws XMLStreamException       the xml stream exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    static PlatformCapability xmlTestCaseHandler(XMLEventReader eventReader)
            throws XMLStreamException, IllegalArgumentException, IOException, URISyntaxException {

    	PlatformCapability platformCapability = new PlatformCapability();
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    System.out.println("Next XML Start Tag: " + startElement.getName().getLocalPart());
                    switch (startElement.getName().getLocalPart().toUpperCase()) {
                        case "PLATFORM":
                            System.out.println("Processing Platform");
                            if(!platformCapability.setPlatform(eventReader)) {
                                System.out.println("Error while processing Platform.");
                                throw new IllegalArgumentException("XML file is malformed");
                            }
                            break;
                        case "BROWSER":
                            System.out.println("Processing Browser");
                            if(!platformCapability.setBrowser(eventReader)) {
                                System.out.println("Error while processing Browser.");
                                throw new IllegalArgumentException("XML file is malformed");
                            }
                            break;
                        default:
                            continue;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    System.out.println("Next XML End Tag: "+ endElement.getName().getLocalPart());
                    switch (endElement.getName().getLocalPart().toUpperCase())
                    {
                        case "CAPABILITY":
                            if(!platformCapability.validate()) {
                                System.out.println("Error in Validating PlatformCapability.");
                                throw new IllegalArgumentException("Platform Name is mandatory while defining a platformCapability");
                            }
                            System.out.println(platformCapability.toString());
                            return platformCapability;

                        default:
                            continue;
                    }
            }

        }
        throw new IllegalArgumentException("XML file is malformed");
    }


   /* *//**
     * Gets the desired capabilities for chrome browser.
     *
     * @return the capabilities for chrome
     *//*
    private DesiredCapabilities getDesiredCapabilitiesForChrome()
    {
        CommandLineParser parser = CommandLineParser.getCommandLineParser();
        System.setProperty("webdriver.chrome.driver", parser.getChromeDriverPath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-web-security");
        //options.setExperimentalOption("web-security", 2);
        options.addArguments("user-data-dir=" + parser.getChromeProfilePath());
        //options.addArguments("--disable-web-security");
        //options.addArguments("-incognito");
        //options.addArguments("-disable-cache");
        //options.addExtensions(new File("C:\\Eclipse Automation\\visual plugin\\extension_2_8.crx"));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //capabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);

        return capabilities;
    }

    *//**
     * Instantiates the WebDriver.
     *
     * @return the WebDriver
     *//*
    private WebDriver getLocalWebDriver()
    {
        DesiredCapabilities cap = getDesiredCapabilitiesForChrome();
        System.out.println(cap.toString());
        WebDriver webDriver = null;
        switch (browser.toUpperCase())
        {
            case "CHROME":
                webDriver = new ChromeDriver(getDesiredCapabilitiesForChrome());
                break;

            case "SAFARI":
                DesiredCapabilities safariCapabilities = DesiredCapabilities.safari();
                safariCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                safariCapabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);
                webDriver = new SafariDriver();
                bringSafariToFront();

        }
        return webDriver;
    }

    *//**
     * Instantiates the Remote WebDriver.
     *
     * @return the Remote WebDriver
     *//*
    private RemoteWebDriver getRemoteDriver()
    {
        DesiredCapabilities capabilities;
        if(browser.toUpperCase().equals("CHROME"))
        {
            capabilities = getDesiredCapabilitiesForChrome();
        }
        else
        {
            capabilities = new DesiredCapabilities();
        }
        System.out.println("Setting browser name as: " + browser.toLowerCase() + " and platform as: " + platform);
        capabilities.setBrowserName(browser.toLowerCase());
        capabilities.setPlatform(platform);
        RemoteWebDriver r = new RemoteWebDriver(capabilities);
        return r;
    }

    *//**
     * Gets web driver.
     *
     * @return the web driver or remote webdriver
     *//*
    public WebDriver getWebDriver()
    {
        if(this.instances > 0)
            return getRemoteDriver();
        return getLocalWebDriver();

    }

    *//**
     * Bring safari to front.
     *
     *//*
    public void bringSafariToFront()
    {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("AppleScript");

        try {
            engine.eval("tell application \"Safari\"");
            engine.eval("set theWindows to windows");
            engine.eval("set win1 to item 1 of theWindows");
            engine.eval("tell win1");
            engine.eval("set visible to true");
            engine.eval("end tell");
            engine.eval("end tell");
        }
        catch (Exception e)
        {}

    }*/
}
