package com.gl.webautomation.webdriver;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.gl.webautomation.compatibility.PlatformCapabilitiesManager;
import com.gl.webautomation.compatibility.PlatformCapability;
import com.gl.webautomation.utils.WebDriverUtils;

public class InitializeWebDriver {
	
	public static WebDriver driver = null;

	public static void initializeWebDriver() {
		
			ClassLoader classLoader;
	        String driverFile = "";
	       // WebDriver driver = null;
			try {
				List<PlatformCapability> capabilitiesList   = PlatformCapabilitiesManager.getManager().getPlatformCapabilityList();
				classLoader = Class.forName("com.gl.webautomation.webdriver.InitializeWebDriver").getClassLoader();
				if(capabilitiesList.get(0).getBrowser().equalsIgnoreCase("chrome")) {
					driverFile = classLoader.getResource(WebDriverUtils.chromedriver).getFile();
					System.setProperty("webdriver.chrome.driver", driverFile);
					driver = (new PlatformCapability()).getWebDriver();
				}
				else if(capabilitiesList.get(0).getBrowser().equalsIgnoreCase("firefox")) {
					driverFile = classLoader.getResource(WebDriverUtils.firefoxdriver).getFile();
					System.setProperty("webdriver.firefox.driver", driverFile);
					driver = (new PlatformCapability()).getWebDriver();
				}
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}

}
