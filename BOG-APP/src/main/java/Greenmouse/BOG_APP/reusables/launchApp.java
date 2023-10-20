package Greenmouse.BOG_APP.reusables;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class launchApp {
	WebDriver driver;
	// ExtentReports extent;

	// initialize Chrome driver
	public WebDriver init() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		driver = new ChromeDriver(options);

		// invoke listener
		TestListener listener = new TestListener();
		listener.setDriver(driver);
		return driver;

	}

	public void launch() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://buildonthego.com/");
		driver.findElement(By.xpath("//*[contains(text(), 'Got It')]")).click();
	}

	public void login() {
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+dan@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// driver.findElement(By.xpath("//p[text()='Switch']")).click();

		// driver.findElement(By.xpath("(//button[text()='Switch'])[2]")).click();

		driver.findElement(By.cssSelector("img[alt='boglogo']")).click();
		
	}

	public void buy() throws InterruptedException {
		
		driver.findElement(By.xpath("(//a[@href='/shop'])[1]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[text()='Products']")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".grid-2")));

		driver.findElement(By.xpath("//p[text()='SAND TO LAGOS MAINLAND']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add To Cart']")));

		driver.findElement(By.xpath("//button[text()='Add To Cart']")).click();

		driver.findElement(By.xpath("//a[@href='/carts'][1]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='PROCEED TO CHECKOUT']")));

		driver.findElement(By.xpath("//p[text()='PROCEED TO CHECKOUT']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".max-h-103")));

		driver.findElement(By.xpath("//p[text()='Delivery Options']")).click();
		driver.findElement(By.xpath("//p[text()='Home Address']")).click();
		driver.findElement(By.xpath("//input[@type='tel']")).clear();
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("9087878787");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nearest_address")));

		WebElement selectText = driver.findElement(By.id("nearest_address"));
		Select opt = new Select(selectText);
		opt.selectByIndex(1);

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		driver.findElement(By.xpath("//button[text()='CHECKOUT']")).click();

		// Thread.sleep(10000);

		// WebDriverWait of Explicit Wait
		// WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));

		// FluentWait Explicit Wait
		// Wait<WebDriver> w = new FluentWait<WebDriver>(driver)
		// .withTimeout(Duration.ofSeconds(10))
		// .pollingEvery(Duration.ofSeconds(2))
		// .ignoring(NoSuchElementException.class);
		// w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".card__selec")));

		driver.switchTo().frame("WVsg9");

		WebElement switchCard = driver.findElement(By.xpath("//button[text()=' Use another card ']"));
		switchCard.click();

		// Find the card number input field and enter a test card number
		WebElement cardNumberField = driver.findElement(By.name("card_number"));
		cardNumberField.sendKeys("4242424242424242");

		// Find the expiration date input field and enter a test expiration date
		WebElement expiryField = driver.findElement(By.name("expiry"));
		expiryField.sendKeys("12/23");

		// Find the CVV input field and enter a test CVV
		WebElement cvvField = driver.findElement(By.name("cvv"));
		cvvField.sendKeys("123");

		// Find the Pay button and click on it to proceed with payment
		WebElement payButton = driver.findElement(By.id("pay-button"));
		payButton.click();

		// You might want to add assertions or further verifications here based on the
		// expected behavior
		// For example, verifying the success or failure message after the payment
		// attempt.

		// Switch back to the main content of the page (outside the iframe)
		driver.switchTo().defaultContent();
	}

	public void requestService() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("(//a[@href='/services'][1])")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[text()='Drawing']")).click();
		driver.findElement(By.xpath("//div[text()='Drawings']")).click();
		driver.findElement(By.xpath("//input[@name='text-1694527642582']")).sendKeys("+2349098989878");
		driver.findElement(By.xpath("//input[@value='Architectural']")).click();
		// input[@name='file-1694527740276']

		// locate the file input webElement
		WebElement fileInput = driver.findElement(By.xpath("//input[@name='file-1694527740276']"));
		// provide the path to be uploaded
		String filePath = "/Users/user/Desktop/Icons/fleurs.png";
		fileInput.sendKeys(filePath);

		WebElement fileInput2 = driver.findElement(By.xpath("//input[@name='file-1694527761687']"));
		// provide the path to be uploaded
		String filePath2 = "/Users/user/Desktop/Icons/fleurs.png";
		fileInput2.sendKeys(filePath2);
		
		driver.findElement(By.xpath("//input[@name='text-1694527851979']")).sendKeys("Ikeja");
		driver.findElement(By.xpath("//input[@value='Residential']")).click();
		driver.findElement(By.xpath("//input[@value='Bungalow']")).click();
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		
//		
//		driver.findElement(By.xpath("//div[text()='Wiring']")).click();
//
//		wait.until(ExpectedConditions
//				.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='text-1681394431611']")));
//		driver.findElement(By.cssSelector("input[name='text-1681394431611']")).sendKeys("DANIEL");
//		
//		driver.findElement(By.cssSelector("input[name='text-1681394450912']")).sendKeys("2 METALBOX ROAD");
//		
//		driver.findElement(By.cssSelector("textarea[name='textarea-1681394483466']"))
//				.sendKeys("This project is for full house wiring with standard materials");
//		
//		driver.findElement(By.cssSelector("input[name='radio-group-1681394531757']")).click();
//		
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("input[name='number-1681394594759']")).sendKeys("45");
//		
//		driver.findElement(By.cssSelector("input[name='date-1681394618827']")).sendKeys("12/20/2024");
//		
//		Thread.sleep(5000);
//		//locate the file input webElement
//		WebElement fileInput = driver.findElement(By.cssSelector("input[name='file-1681394644646']"));	
//		//provide the path to be uploaded
//		String filePath = "C:\\Users\\user\\Downloads\\Screenshot.png";
//		fileInput.sendKeys(filePath);
//		
//		driver.findElement(By.xpath("//button[text()='Submit']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
//		driver.findElement(By.xpath("//button[text()='OK']")).click();
//
//		// driver.switchTo().alert().accept();
//		Thread.sleep(5000);
//
//		// To go back and Delete the request
//		driver.findElement(By.xpath("//button[text()='Dashboard']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='QA Product Green']")));
//		driver.findElement(By.xpath("//p[text()='Projects']")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[text()='My Projects'])[2]")));
//
//		// Locate the WebElement containing the date
//		WebElement dateElement = driver.findElement(By.xpath("//tbody/tr[1]/td[6]"));
//		String webDate = dateElement.getText();
//		String websiteDate = webDate.toString();
//		System.out.println("Website Date: " + websiteDate);
//
//		// Get the current date
//		LocalDate currentDate = LocalDate.now();
//
//		// Format the current date as per your requirements
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM /dd /yyyy");
//		String formattedCurrentDate = currentDate.format(formatter);
//		String cDate = formattedCurrentDate.toString();
//		System.out.println("Current Date: " + cDate);
////
//		// Compare the dates
//		if (cDate.equals(websiteDate)) {
//			System.out.println("The dates are equal.");
//			Thread.sleep(5000);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//tbody/tr[1]/td[7] //button[@aria-haspopup='menu']"))));
//			
//			driver.findElement(By.xpath("//tbody/tr[1]/td[7] //button[@id=':r2e:']")).click();
//			driver.findElement(By.xpath("//button[text()='Delete']")).click();
//			driver.findElement(By.xpath("//button[text()='DELETE']")).click();
//			driver.findElement(By.xpath("//button[text()='OK']")).click();
//
//		}
//    else if (cDate != websiteDate)
//    {
//        System.out.println("The current date is before the WebElement date.");
//    } 
//		else {
//			System.out.println("The current date is not equals to the WebElement date.");
//		}

		// extent.flush(); // stop listening mood and generate report each time the test
		// is run

	}

	public void serviceReg() {
		driver.findElement(By.xpath("//button[text()='Get Started']")).click();
		driver.findElement(By.xpath("//p[text()='Render services to clients']")).click();
		driver.findElement(By.id("fname")).sendKeys("QA-Service 4");
		driver.findElement(By.id("lname")).sendKeys("Green");
		driver.findElement(By.id("email")).sendKeys("greenmousedev+service41@gmail.com");
		driver.findElement(By.id("company_name")).sendKeys("DandyTech");
		driver.findElement(By.cssSelector("input[type='tel']")).sendKeys("8067564758");
		WebElement serviceType = driver.findElement(By.cssSelector("select[id='serviceTypeId']"));
		Select service = new Select(serviceType);
		service.selectByVisibleText("Wiring");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.id("password2")).sendKeys("Dandytech@2022");
		WebElement about = driver.findElement(By.id("aboutUs"));
		Select aboutUs = new Select(about);
		aboutUs.selectByVisibleText("WhatsApp");
		driver.findElement(By.id("terms")).click();

		// driver.switchTo().frame("a-ickm57az7hco");
		// driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-checkmark']")).click();
		// driver.switchTo().defaultContent();

		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();

	}

	public void loginService() {
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+draw5@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	public void CompleteKYC() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Verify']")));
		driver.findElement(By.xpath("//button[text()='Verify']")).click();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("NOKIA");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("greenmouseapp+draw@gmail.com");

//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='number'])[1]")));
//		element.sendKeys("+234 90989878");

		// driver.findElement(By.xpath("(//input[@class='w-full p-2 mt-2 border
		// border-gray-400 rounded'])[2]")).sendKeys("+234 90989878");

		driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
		driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("+234 90989878");

		driver.findElement(By.xpath("(//textarea)[1]")).sendKeys("2 metalbox rd");

		driver.findElement(By.xpath("(//textarea)[2]")).sendKeys("2 metalbox rd");

		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Organisation Information']")).click();
		WebElement orgs = driver
				.findElement(By.xpath("//select[@class='w-full mt-2 p-2 border border-gray-400 rounded']"));
		Select org = new Select(orgs);
		org.selectByVisibleText("Partnership");
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("None");
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys("12/5/2021");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Mr. OKo");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("MD");
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("+234 8098988787");
		driver.findElement(By.xpath("(//input[@type='email'])[1]")).sendKeys("greenmouseapp+service1@gmail.com");
		driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("+234 8087767645");
		driver.findElement(By.xpath("(//input[@type='email'])[2]")).sendKeys("greenmouseapp+service1@gmail.com");
		driver.findElement(By.xpath("//textarea[@class='w-full p-2 mt-2 border border-gray-400 rounded h-24']"))
				.sendKeys("CAC, Greenmouse");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Tax Details and Permits']")).click();
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("57686");
		driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("57686");
		driver.findElement(By.xpath("//textarea[@class='w-full p-2 mt-2 border border-gray-400 rounded h-24']"))
				.sendKeys("CAC, Greenmouse, NOKIA");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Work/Job Execution Experience']")).click();
		driver.findElement(By.xpath("//button[text()='Add Job Experience']")).click();
		driver.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys("QA Tester");
		driver.findElement(By.xpath("(//input[@name='name'])[2]")).sendKeys("80000000");
		driver.findElement(By.xpath("(//input[@name='name'])[3]")).sendKeys("12/3/2020");
		WebElement fileInput = driver.findElement(By.xpath("(//input[@name='name'])[4]"));
		// Provide the file path to be uploaded
		String filePath = "//Users//user//Downloads//Plumber.jpeg";
		fileInput.sendKeys(filePath);
		driver.findElement(By.xpath("(//input[@name='name'])[5]")).sendKeys("2");
		driver.findElement(By.xpath("//textarea[@class='w-full mt-2 h-24 p-2 border border-gray-400 rounded']"))
				.sendKeys("None");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Financial Data']")).click();
		WebElement options = driver.findElement(By.xpath("//select[@id='bank_name']"));
		Select option = new Select(options);
		option.selectByVisibleText("Access Bank (Diamond)");
		driver.findElement(By.xpath("//input[@name='account_number']")).sendKeys("0023028815");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//textarea[@name='banker_address']")).sendKeys("2 metalbox rd");
		driver.findElement(By.xpath("(//input[@name='account_type'])[2]")).click();
		driver.findElement(By.xpath("overdraft_facility")).sendKeys("None");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Upload Documents']")).click();
		WebElement fileInput2 = driver.findElement(By.xpath("//input[@name='Company_Corporate_Profile']"));
		String filePath2 = "//Users//user//Desktop//Icons//tester.png";
		fileInput2.sendKeys(filePath2);
		WebElement fileInput3 = driver.findElement(By.xpath("//input[@name='Organizational_Chart']"));
		String filePath3 = "//Users//user//Desktop//Icons//tester.png";
		fileInput3.sendKeys(filePath3);
		WebElement fileInput4 = driver.findElement(By.xpath("//input[@name='Certificate_of_Registration']"));
		String filePath4 = "//Users//user//Desktop//Icons//tester.png";
		fileInput4.sendKeys(filePath4);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

	}

	public void updateKYC() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//p[text()='Settings']")).click();

		driver.findElement(By.xpath("//button[text()='Update KYC']")).click();

		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("NOKIA");

		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("greenmouseapp+service1@gmail.com");

		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("greenmouseapp+service1@gmail.com");

		driver.findElement(By.xpath("(//input[@type='number'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("+234 90989878");

		driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();

		driver.findElement(By.xpath("(//input[@type='number'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("+234 90989878");

		driver.findElement(By.xpath("(//textarea)[1]")).clear();
		driver.findElement(By.xpath("(//textarea)[1]")).sendKeys("2 metalbox rd");

		driver.findElement(By.xpath("(//textarea)[2]")).clear();
		driver.findElement(By.xpath("(//textarea)[2]")).sendKeys("2 metalbox rd");

		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Organisation Information']")).click();

		driver.findElement(By.xpath("//div[text()='Organisation Information']")).click();

		WebElement orgs = driver
				.findElement(By.xpath("//select[@class='w-full mt-2 p-2 border border-gray-400 rounded']"));
		Select org = new Select(orgs);
		org.selectByVisibleText("Partnership");

		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("None");
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys("12/5/2021");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Mr. OKo");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("MD");
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("+234 8098988787");
		driver.findElement(By.xpath("(//input[@type='email'])[1]")).sendKeys("greenmouseapp+service1@gmail.com");
		driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("+234 8087767645");
		driver.findElement(By.xpath("(//input[@type='email'])[2]")).sendKeys("greenmouseapp+service1@gmail.com");
		driver.findElement(By.xpath("//textarea[@class='w-full p-2 mt-2 border border-gray-400 rounded h-24']"))
				.sendKeys("CAC, Greenmouse");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Tax Details and Permits']")).click();
		driver.findElement(By.xpath("(//input[@type='number'])[1]")).sendKeys("57686");
		driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("57686");
		driver.findElement(By.xpath("//textarea[@class='w-full p-2 mt-2 border border-gray-400 rounded h-24']"))
				.sendKeys("CAC, Greenmouse, NOKIA");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Work/Job Execution Experience']")).click();
		driver.findElement(By.xpath("//button[text()='Add Job Experience']")).click();
		driver.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys("QA Tester");
		driver.findElement(By.xpath("(//input[@name='name'])[2]")).sendKeys("80000000");
		driver.findElement(By.xpath("(//input[@name='name'])[3]")).sendKeys("12/3/2020");
		WebElement fileInput = driver.findElement(By.xpath("(//input[@name='name'])[4]"));
		// Provide the file path to be uploaded
		String filePath = "//Users//user//Downloads//Plumber.jpeg";
		fileInput.sendKeys(filePath);
		driver.findElement(By.xpath("(//input[@name='name'])[5]")).sendKeys("2");
		driver.findElement(By.xpath("//textarea[@class='w-full mt-2 h-24 p-2 border border-gray-400 rounded']"))
				.sendKeys("None");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Financial Data']")).click();
		WebElement options = driver.findElement(By.xpath("//select[@id='bank_name']"));
		Select option = new Select(options);
		option.selectByVisibleText("Access Bank (Diamond)");
		driver.findElement(By.xpath("//input[@name='account_number']")).sendKeys("0023028815");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//textarea[@name='banker_address']")).sendKeys("2 metalbox rd");
		driver.findElement(By.xpath("(//input[@name='account_type'])[2]")).click();
		driver.findElement(By.xpath("overdraft_facility")).sendKeys("None");
		driver.findElement(By.xpath("//button[text()='Save & Continue']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

		driver.findElement(By.xpath("//div[text()='Upload Documents']")).click();
		WebElement fileInput2 = driver.findElement(By.xpath("//input[@name='Company_Corporate_Profile']"));
		String filePath2 = "//Users//user//Desktop//Icons//tester.png";
		fileInput2.sendKeys(filePath2);
		WebElement fileInput3 = driver.findElement(By.xpath("//input[@name='Organizational_Chart']"));
		String filePath3 = "//Users//user//Desktop//Icons//tester.png";
		fileInput3.sendKeys(filePath3);
		WebElement fileInput4 = driver.findElement(By.xpath("//input[@name='Certificate_of_Registration']"));
		String filePath4 = "//Users//user//Desktop//Icons//tester.png";
		fileInput4.sendKeys(filePath4);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();

	}

	public void captureScreenshot(String screenshotName) {
		try {
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destinationFile = new File(
					"//Users//user//eclipse-workspace//CaptureTestFailed//reports//" + screenshotName + ".png");
			Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Screenshot captured: " + destinationFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result.getName());
		}
		driver.quit();
	}

}
