package com.gl.webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gl.webautomation.uielements.UIElement;
import com.gl.webautomation.utils.BaseClass;

public class HomePage extends BaseClass{
	
	

	public HomePage(String pageName) throws Exception {
		populatePageData(pageName);
	}
	
	

	public void inputSearchData(String inputData) {
		WebDriver driver = getWebDriver();
		driver.get("https://www.google.com/");
		UIElement element = elementsMap.get("SearchInputBox");
		System.out.println(element);
		//WebElement inputElement = driver.findElement(element.getName());
		/*WebElement inputElement = driver.findElement(elementsMap.get("SearchInputBox"));
		inputElement.sendKeys(inputData);
		WebElement clickElement = driver.findElement(By.name("btnk"));
		clickElement.click();*/
	}
}
