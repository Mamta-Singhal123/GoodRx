package endTests;

import org.testng.annotations.Test;

import pageObjects.HomePage;

import com.relevantcodes.extentreports.LogStatus;

public class DemoTest extends BaseTest{
	
	@Test
	public void AssignmentTest()
	{
		test=extent.startTest("DemoTest");
		test.log(LogStatus.INFO, "Test started");
		HomePage homePage=new HomePage(driver, test);
		homePage.serachText("Amoxicillin");
		homePage.validatePrices();
		assertFalse(true, test);
		extent.endTest(test);
	}


}
