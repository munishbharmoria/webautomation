package com.gl.webautomation.uielements;

import org.openqa.selenium.By;


public class UIElement {
    private By by;
    private String name;
    String locatorType;

    public String getName()
    {
        return name;
    }

    
    @Override
	public String toString() {
		return "UIElement [by=" + by + ", name=" + name + ", locatorType=" + locatorType + "]";
	}


	/**
     * Instantiates a new Ui element.
     *
     * @param elementName the element name
     * @param locatorType the locator type
     * @param locator     the locator
     * @param logger      generates the log4j logs
     */
    public UIElement(String elementName, String locatorType, String locator)
    {
        name = elementName;
        this.locatorType = locatorType;
        by = getByObjectForElementLocator(locatorType, locator);
    }
    

       /**
     * Get Element Locator by object.
     *
     * @param type the locator type of the element
     * @param locator the locator of the element of the defined type
     */
    public By getByObjectForElementLocator(String type, String locator)
    {
        if ("classname".equals(type)) {
            return By.className(locator);
        } else if ("css".equals(type)) {
            return By.cssSelector(locator);
        } else if ("id".equals(type)) {
            return By.id(locator);
        } else if ("linktext".equals(type)) {
            return By.linkText(locator);
        } else if ("name".equals(type)) {
            return By.name(locator);
        } else if ("partiallinktext".equals(type)) {
            return By.partialLinkText(locator);
        } else if ("tag".equals(type)) {
            return By.tagName(locator);
        } else if ("xpath".equals(type)) {
            return By.xpath(locator);
        }
        throw new IllegalArgumentException("Invalid Element Locator Type Specified");
    }
}
