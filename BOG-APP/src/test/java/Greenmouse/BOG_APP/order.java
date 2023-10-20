package Greenmouse.BOG_APP;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.BOG_APP.reusables.launchApp;

public class order {
WebDriver driver;
//ExtentReports extent;


	@Test
	public void makeOder() throws InterruptedException {
		
	launchApp launcher = new launchApp();	
	//extent.createTest("makeOrder");
	
	launcher.init();
	launcher.launch();
	launcher.login();
	launcher.buy();
	launcher.captureScreenshot(null);
	launcher.tearDown(null);
	
	}
	

	
    

}
