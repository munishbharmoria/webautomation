package com.gl.webautomation.utils;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.gl.webautomation.parsers.JSONParser;
import com.gl.webautomation.uielements.UIElement;
import com.gl.webautomation.webdriver.InitializeWebDriver;

public class BaseClass {
	
	public WebDriver getWebDriver() {
		return InitializeWebDriver.getDriver();
	}
	
	protected HashMap<String, UIElement> elementsMap = new HashMap<String, UIElement>();

	public void populatePageData(String name) throws Exception, IOException {

		String path = "elementlocators/" + name + ".json";
		ClassLoader classLoader = Class.forName("com.gl.webautomation.utils.BaseClass").getClassLoader();
		String pageJsonFile  = classLoader.getResource(path).getFile();
		//		
		JSONObject jsonObject = JSONParser.getJSONObjectForAResource(classLoader.getResource(path));
		
		//				
		
		//JSONObject jsonObject = JSONParser.getJSONObjectForAPage(pageJsonFile);
		System.out.println(jsonObject);
		
		 JSONArray arr = jsonObject.getJSONArray("uiElements");
	        for (int i = 0; i < arr.length(); i++)
	        {
	            String elementName = arr.getJSONObject(i).getString("elementName");
	            String byId = arr.getJSONObject(i).getString("by");
	            String locator = arr.getJSONObject(i).getString("locator");

	            UIElement e = new UIElement(elementName, byId, locator);
	            elementsMap.put(elementName, e);
	        }
		
	}
	
	


}
