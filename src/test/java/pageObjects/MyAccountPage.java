package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//h1[text()='My Account']")
		WebElement myAcc;
	@FindBy (xpath = "//a[@class='dropdown-toggle']//*[text()='My Account']")
	WebElement myAccount;
	
	@FindBy (xpath = "//a[text()='Logout' and @class='dropdown-item']")
		WebElement logout;

	public boolean confirmMessage() {
		
		try {
//			Thread.sleep(5000);
			return (myAcc.isDisplayed());
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void clickMyAccount() {
		myAccount.click();
	}
	
	public void clickLogout() {
		logout.click();
	}
	
	
}
