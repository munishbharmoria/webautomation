package com.gl.webautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gl.webautomation.utils.BaseClass;

public class HomePage extends BaseClass{

	public void inputSearchData(String inputData) {
		WebDriver driver = getWebDriver();
		driver.get("https://www.google.com/");
		WebElement inputElement = driver.findElement(By.name("q"));
		inputElement.sendKeys(inputData);
		WebElement clickElement = driver.findElement(By.name("btnk"));
		clickElement.click();
	}
}
