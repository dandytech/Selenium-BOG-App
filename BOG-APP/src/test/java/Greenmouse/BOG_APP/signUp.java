package Greenmouse.BOG_APP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;



public class signUp {
	WebDriver driver;

	@Test
	public void ServicePartnerSignUp() throws InterruptedException
	{
		Greenmouse.BOG_APP.reusables.launchApp reg = new Greenmouse.BOG_APP.reusables.launchApp();
		reg.init();
		//request.report();
		reg.launch();
		reg.serviceReg();
	}

}
