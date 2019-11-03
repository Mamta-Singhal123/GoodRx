package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.internal.DynamicGraph.Status;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class SeleniumUtils {

	ExtentTest test;
	public WebDriver driver;
	public SeleniumUtils(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
	}
	
	public void clickElement(WebElement element,String elementName)
	{
		test.log(LogStatus.INFO, "clicking on element "+elementName);
		element.click();
	}
	public void sendKeys(WebElement element,String data,String elementName)
	{
		test.log(LogStatus.INFO, "sending to the element "+elementName);
		element.sendKeys(data);
	}
	
	public void sleep()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sleep(int sec)
	{
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
