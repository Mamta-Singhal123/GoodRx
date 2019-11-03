package pageObjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.SeleniumUtils;

import com.relevantcodes.extentreports.ExtentTest;

public class HomePage extends SeleniumUtils{

	
	@FindBy(how=How.CLASS_NAME,using="hero-search")
	WebElement searchText;
	@FindBy(how=How.CLASS_NAME,using="btn hero-search-submit")
	WebElement searchButton;
	public HomePage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public void serachText(String searchString)
	{
		sendKeys(searchText, searchString, "searchText");
		sleep();
		searchText.sendKeys(Keys.ENTER);
		sleep();
	}

	public void validatePrices()
	{
		String rowsPath="//div[@id='a11y-prices-start']/ul[@role='navigation']/li";
		List<WebElement> rows=driver.findElements(By.xpath(rowsPath));
		for(int i=0;i<rows.size();++i)
		{
			int index=i+1;
			String buttonxpath="//div[@id='a11y-prices-start']/ul[@role='navigation']/li["+(i+1)+"]/div[2]/button";
			String primeValue="//div[@id='a11y-prices-start']/ul[@role='navigation']/li["+index+"]/div[1]/div[3]/div";
			//rows=driver.findElements(By.xpath(rowsPath));
			String price=driver.findElement(By.xpath(primeValue)).getText().split("\n")[1];
			price=price.substring(1,price.length()-1);
			String value=getPrice(driver.findElement(By.xpath(buttonxpath)));
			System.out.println(price);
			System.out.println(value);
			Assert.assertEquals(price, value);
		}
		
	}
	public String getPrice(WebElement element)
	{
		Set<String> preHandles=driver.getWindowHandles();
		element.click();
		sleep(5);
		Set<String> postHandles=driver.getWindowHandles();
		postHandles.removeAll(preHandles);
		driver.switchTo().window(postHandles.toArray()[0].toString());
		String value= driver.findElement(By.xpath("//span[@class='value pull-left']")).getText();
	//	driver.close();
		driver.switchTo().window(preHandles.toArray()[0].toString());
		return value.substring(0,value.length()-1);
	}
}
