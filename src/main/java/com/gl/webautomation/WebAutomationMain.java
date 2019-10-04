package com.gl.webautomation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.gl.webautomation.compatibility.PlatformCapabilitiesManager;
import com.gl.webautomation.compatibility.PlatformCapability;
import com.gl.webautomation.pageobjects.HomePage;
import com.gl.webautomation.testcases.HomePageTestCase;
import com.gl.webautomation.testmanager.TestData;
import com.gl.webautomation.testmanager.TestManager;
import com.gl.webautomation.utils.PageFactory;
import com.gl.webautomation.utils.PlatformCapabilityUtils;
import com.gl.webautomation.utils.WebDriverUtils;
import com.gl.webautomation.webdriver.InitializeWebDriver;

// Main to startup automation test
public class WebAutomationMain 
{
	
    public static void main( String[] args ) throws IOException, URISyntaxException, Exception
    {
        // initialize platform capabilities
    	PlatformCapabilitiesManager.initPlatformCapabilities();
    	
    	// initialize test data
        TestManager.initTestData();
        
        // initialize web driver
        InitializeWebDriver.initializeWebDriver();
        
        //Verifying data
        TestData loginData = TestManager.getTestManager().getTestData("login");
        System.out.println(loginData.getParamValue("username"));
        System.out.println(loginData.getParamValue("password"));
        
        //Verifying data
        TestData emailData = TestManager.getTestManager().getTestData("composeemail");
        System.out.println(emailData.getParamValue("sendtoemail"));
        System.out.println(emailData.getParamValue("emailsubject"));
        System.out.println(emailData.getParamValue("emailbody"));
        
        //Verifying data
        TestData regressionTestingdata = TestManager.getTestManager().getTestData("regressiondata");
        String regressionTestcases = regressionTestingdata.getParamValue("testcases");        
        System.out.println("regression testcases = "+regressionTestcases);
        
        TestData sanityTestingdata = TestManager.getTestManager().getTestData("sanitydata");
        String sanityTestcases = sanityTestingdata.getParamValue("testcases");        
        System.out.println("sanity testcases = "+sanityTestcases);
        
        List<PlatformCapability> capabilitiesList   = PlatformCapabilitiesManager.getManager().getPlatformCapabilityList();
        System.out.println(capabilitiesList);
        
        // Test cases
        
        HomePageTestCase case1 = new HomePageTestCase();
        case1.verifySearch();
        
    }
}
