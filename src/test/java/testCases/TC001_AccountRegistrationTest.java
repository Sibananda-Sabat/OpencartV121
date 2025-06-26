package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseCase;

public class TC001_AccountRegistrationTest extends BaseCase {
	
	
	/*
	 * @BeforeClass public void hitUrl() {
	 * driver.get("http://localhost/opencart/upload/"); }
	 */
	
	@Test(groups = {"regression","master"})
	public void accountRegistration() throws InterruptedException {
		
		try {
		logger.info("***Starting TC001_AccountRegistrationTest*****");
		
		HomePage home = new HomePage(driver);
		
		home.clickMyAcc();
		logger.info("***Clicked on My account*****");
		
		home.clickRegister();
		logger.info("***Clicked on Register*****");
		
		RegistrationPage regpage = new RegistrationPage(driver);
		
		logger.info("***Providing Details*****");
		regpage.setFirstName(randomStringGenerate(5).toUpperCase());
		regpage.setLastName(randomStringGenerate(5).toUpperCase());
		regpage.setEmilName(randomStringGenerate(5)+"@gmail.com");
		regpage.setPasswordName(randomAlphaNumericGenerate(5));
		
		regpage.clicknewsletter();
		regpage.clickAgree();
		regpage.clickContinue();
		
		logger.info("***Validating Confirmation message*****");
		String confirmMessage = regpage.getConfirmation();
		Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
		
		regpage.clickContinue2();
		
		System.out.println(confirmMessage);
		}
		catch(Exception e) {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		logger.info("***Finished testcase*****");
	}
	
}
