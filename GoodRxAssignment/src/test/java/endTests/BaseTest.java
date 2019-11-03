package endTests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BaseTest {
	ExtentReports extent =null;
	ExtentTest test;
	WebDriver driver;
	@BeforeTest
	public void launchBrowser()
	{
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.goodrx.com/");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	@AfterTest
	public void quitBrowser()
	{
		driver.quit();
	}
	@BeforeSuite
	public void createReports()
	{
		String reportName="./reports/ExtentReport_"+System.currentTimeMillis()+".html";
		
	//	ExtentKlovReporter klov = new ExtentKlovReporter("project", "build");

		 extent = new ExtentReports(reportName);
		
		

	}
	@AfterSuite
	public void flushReports()
	{
		extent.flush();
	}
	
	public void assertIntValues(int actual,int expected,ExtentTest test){
		
	}
	
	public void assertTrue(boolean result,ExtentTest test){
		if(!result)
			
				test.log(LogStatus.FAIL,test.addScreenCapture(getScreenshotPath()));
			
			Assert.assertTrue(result);
	}
	public void assertFalse(boolean result,ExtentTest test){
		if(result)
			test.log(LogStatus.FAIL,test.addScreenCapture(getScreenshotPath()));
			Assert.assertTrue(result);
	}
	public String getScreenshotPath(){
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path="./screenshots/"+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
