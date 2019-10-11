package com.gl.webautomation.testcases;

import com.gl.webautomation.pageobjects.HomePage;
import com.gl.webautomation.utils.PageFactory;

public class HomePageTestCase {
	
	
	public void verifySearch() throws Exception {
		HomePage homePage = PageFactory.getPageFactory().getHomePage();
		homePage.inputSearchData("facebook");
	}

}
