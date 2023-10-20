package Greenmouse.BOG_APP;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Greenmouse.BOG_APP.reusables.TestListener;
import Greenmouse.BOG_APP.reusables.launchApp;

public class KYC extends TestListener {

	WebDriver driver;
	// ExtentReports extent;

	@Test
	public void completeKYC() throws InterruptedException {

		launchApp launcher = new launchApp();
		launcher.init(); // launch driver

		launcher.launch();
		launcher.loginService();
		Thread.sleep(10000);
		launcher.CompleteKYC();
	}

}
