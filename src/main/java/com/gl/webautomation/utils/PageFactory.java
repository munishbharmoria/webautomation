package com.gl.webautomation.utils;

import com.gl.webautomation.pageobjects.HomePage;

public class PageFactory {
	
	private static PageFactory pageFactory = new PageFactory();

    public static PageFactory getPageFactory() {
        return pageFactory;
    }
	
	public HomePage getHomePage() throws Exception
	{
		//HomePage homePage = new HomePage();
		HomePage homePage = new HomePage("HomePage");
		return homePage;
	}

}
