package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseCase;

public class TC002_LoginTest extends BaseCase {
	
	@Test(groups = {"sanity","master"})
	public void login() {
		logger.info("*****************Starting the TC002_LoginTest*************");
		
		try {
			HomePage home = new HomePage(driver);
			home.clickMyAcc();
			home.clickLogin();
			
			LoginPage lp = new LoginPage(driver);
			
			lp.setEmail(pro.getProperty("email"));
			lp.setPass(pro.getProperty("password"));
			lp.clickLogin();
			
			MyAccountPage myacc = new MyAccountPage(driver);
			
	//		Assert.assertEquals(myacc.confirmMessage(), true,"Login failed");
			Assert.assertTrue(myacc.confirmMessage());
			
			myacc.clickMyAccount();
			myacc.clickLogout();
		}
		
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("*****************Ending the TC002_LoginTest*************");
	}
	
}
