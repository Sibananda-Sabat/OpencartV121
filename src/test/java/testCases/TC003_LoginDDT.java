package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseCase;
import utilities.DataProviders;

/* Data is valid - login success - test pass - logout
 * Data is valid - login failed - test failed
 * 
 * Data is invalid - login success - test failed - logout
 * Data is invalid - login failed - test pass
 */

public class TC003_LoginDDT extends BaseCase {

	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"datadriven"}) //Getting data provider from different class
	public void loginddt(String eml, String pwd, String exp) {
		
		logger.info("*****************Starting the TC003_LoginDDT*************");
		
		try {
			//Home page
			HomePage home = new HomePage(driver);
			home.clickMyAcc();
			home.clickLogin();
			
			//Login page
			LoginPage lp = new LoginPage(driver);
			
			lp.setEmail(eml);
			lp.setPass(pwd);
			lp.clickLogin();
			
			//Myaccount page
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetPage = myacc.confirmMessage();
			
			if (exp.equalsIgnoreCase("valid")) {
				if (targetPage == true) {
					myacc.clickMyAccount();
					myacc.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			else if (exp.equalsIgnoreCase("invalid")) {
				if (targetPage == true) {
					myacc.clickMyAccount();
					myacc.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
	
			}
		}
		
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*****************Ending the TC003_LoginDDT*************");
	}
	
}
