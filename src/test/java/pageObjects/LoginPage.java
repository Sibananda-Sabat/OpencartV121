package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "input[name='email']")
		WebElement email;
	@FindBy(css = "input[name='password']")
		WebElement password;
	@FindBy(xpath = "//button[text()='Login']")
		WebElement login;
	
	public void setEmail(String eml) {
		email.sendKeys(eml);
	}

	public void setPass(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLogin() {
		login.click();
	}


	
}
