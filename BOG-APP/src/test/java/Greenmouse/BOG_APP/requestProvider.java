package Greenmouse.BOG_APP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class requestProvider{
	WebDriver driver;
	@Test
	public void requestServiceProvider() throws InterruptedException
	{
	Greenmouse.BOG_APP.reusables.launchApp request = new Greenmouse.BOG_APP.reusables.launchApp();
	request.init();
	request.launch();
	request.login();
	request.requestService();
	}
	
}
