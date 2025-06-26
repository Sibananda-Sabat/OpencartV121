package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {


	//Constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(name="firstname")
		WebElement firstName;
	
	@FindBy(name="lastname")
		WebElement lastName;
	
	@FindBy(name="email")
		WebElement emailId;
	
	@FindBy(name="password")
		WebElement passWord;
	
	@FindBy(name="newsletter")
		WebElement news;
	
	@FindBy(name="agree")
		WebElement tc;
	
	@FindBy(xpath = "//button[@type='submit' and text()='Continue']")
		WebElement continu;
	
	@FindBy(xpath="//h1[contains(text(),'Your Account Has Been Created')]")
		WebElement confirmMessage;
	
	@FindBy(xpath = "//a[text()='Continue']")
	WebElement continu2;
	
	//Action method
	
	public void setFirstName(String fname) {
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void setEmilName(String email) {
		emailId.sendKeys(email);
	}
	
	public void setPasswordName(String pwd) {
		passWord.sendKeys(pwd);
	}
	
	public void clicknewsletter() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", news);
		
		Thread.sleep(2000);
		
		if (news.isSelected() == true) {
			news.click();
		}
	}
	
	public void clickAgree() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);", tc);
		
		Thread.sleep(2000);
		
		if (tc.isSelected() == false) {
			tc.click();
		}
	}
	
	public void clickContinue() {
		continu.click();
		
		//sol2 
		//continu.submit(); 
		//sol3 
		//Actions act=new Actions (driver); 
		//act.moveToElement (continu).click().perform(); 
		//sol4 
		//JavascriptExecutor js=(JavascriptExecutor)driver; 
		//js.executeScript("arguments[0].click();", continu); 
		//Sol 5 
		//continu.sendKeys (Keys.RETURN); 
		//So16 
		//WebDriverWait mywait = new WebDriverWait (driver, Duration.ofSeconds (10)); 
		//mywait.until (ExpectedConditions.elementToBeClickable (continu)).click();
		
	}
	
	public void clickContinue2() {
		continu2.click();
	}
	
	public String getConfirmation() {
		try {
			return (confirmMessage.getText());
		}
		catch (Exception e) {
			return (e.getMessage());
		}
	}
	
	

	
}
