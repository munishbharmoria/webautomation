package com.gl.webautomation.utils;

import org.openqa.selenium.WebDriver;

import com.gl.webautomation.webdriver.InitializeWebDriver;

public class BaseClass {
	
	public WebDriver getWebDriver() {
		return InitializeWebDriver.getDriver();
	}

}
