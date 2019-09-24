package com.gl.webautomation;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.gl.webautomation.compatibility.PlatformCapabilitiesManager;
import com.gl.webautomation.compatibility.PlatformCapability;
import com.gl.webautomation.testmanager.TestData;
import com.gl.webautomation.testmanager.TestManager;

// Mai
public class WebAutomationMain 
{
    public static void main( String[] args )
    {
        
    	PlatformCapabilitiesManager.init();
    	
    	
        TestManager.init();
        
        TestData loginData = TestManager.getTestManager().getTestData("login");
        System.out.println(loginData.getParamValue("username"));
        System.out.println(loginData.getParamValue("password"));
        
        TestData emailData = TestManager.getTestManager().getTestData("composeemail");
        System.out.println(emailData.getParamValue("sendtoemail"));
        System.out.println(emailData.getParamValue("emailsubject"));
        System.out.println(emailData.getParamValue("emailbody"));
        
        
        
        TestData testingdata = TestManager.getTestManager().getTestData(args[0]);
        String testcases = testingdata.getParamValue("testcases");        
        System.out.println("testcases = "+testcases);
        
        List<PlatformCapability> capabilitiesList   = PlatformCapabilitiesManager.getManager().getPlatformCapabilityList();
        System.out.println(capabilitiesList);
       
        
        
    }
}
