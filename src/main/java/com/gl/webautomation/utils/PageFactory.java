package com.gl.webautomation.utils;

import com.gl.webautomation.pageobjects.HomePage;

import main.java.eclipse.automation.utils.PageFactory;

public class PageFactory {
	
	private static PageFactory pageFactory = new PageFactory();

    public static PageFactory getPageFactory() {
        return pageFactory;
    }
	
	public HomePage getHomePage()
	{
		HomePage homePage = new HomePage();
		return homePage;
	}

}
