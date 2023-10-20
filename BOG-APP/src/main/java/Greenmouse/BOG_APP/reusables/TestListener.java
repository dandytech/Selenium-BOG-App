package Greenmouse.BOG_APP.reusables;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListener implements ITestListener {
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        String reportPath = "//Users//user//eclipse-workspace//BOG-APP//reports//html";
        //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        captureScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshot(String testName) {
        if (driver != null) {
            try {
                String screenshotFilePath = "//Users//user//eclipse-workspace//BOG-APP//reports//screenshot" + testName + ".png";
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                byte[] screenshotData = screenshot.getScreenshotAs(OutputType.BYTES);
                FileUtils.writeByteArrayToFile(new File(screenshotFilePath), screenshotData);
                test.get().addScreenCaptureFromPath(screenshotFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
