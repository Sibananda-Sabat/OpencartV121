package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//constructor
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	//Locators
	
	@FindBy (xpath = "//a[@class='dropdown-toggle']//*[text()='My Account']")
		WebElement myAccount;
	@FindBy(xpath = "//a[text()='Register']")
		WebElement register;
	@FindBy(xpath = "//a[text()='Login']")
		WebElement login;
	
	//Action method
	public void clickMyAcc() {
		myAccount.click();
	}
	
	public void clickRegister() {
		register.click();
	}
	
	public void clickLogin() {
		login.click();
	}
	
}
