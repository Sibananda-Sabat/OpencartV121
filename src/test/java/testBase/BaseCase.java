package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.Logger;

// TO achieve the reuseability

public class BaseCase {
	
public static WebDriver driver;
public Logger logger; //Log4j2
public Properties pro;

	@BeforeClass(groups= {"sanity","regression","master","datadriven"})
	@Parameters({"OS","Browser"})
	public void setup(String os, String br) throws IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
 		pro = new Properties();
 		pro.load(file);
		
		logger = LogManager.getLogger(this.getClass());  //Log4j2
		
		if (pro.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			// The URL will be IP Address of Hub Machine + Hub Port + /wd/hub //http://192.168.13.1:4444/wd/hub or http://localhost:4444/wd/hub
			String huburl = "http://localhost:4444/wd/hub";//"http://192.168.0.104:4444/wd/hub";//"http://localhost:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//OS
			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform (Platform.WIN10); //cap.setPlatform (Platform.MAC);				
			}
			else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform (Platform.MAC); 
			}
			else if (os.equalsIgnoreCase("Linux")) {
				cap.setPlatform (Platform.LINUX); 
			}
			else {
				System.out.println("No matching OS");
				return;
			}
			
			//Browser
			
			switch (br.toLowerCase()) {
			
			case "chrome":
				cap.setBrowserName("chrome"); //cap.setBrowserName("MicrosoftEdge")		
//				cap.setVersion("100");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				cap.setBrowserName("firefox");
				break;
			default:
				Assert.fail();
				return;
			}
			driver = new RemoteWebDriver (new URL(huburl), cap);
			
		}
		
		if(pro.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch (br.toLowerCase()) {
			
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				Assert.fail();
				return;
			}	
			
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
//		driver.get("http://localhost/opencart/upload/");
		driver.get(pro.getProperty("appURL"));
//		file.close();
	}
	
	@AfterClass(groups= {"sanity","regression","master","datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomStringGenerate(int count) {
		String randString = RandomStringUtils.randomAlphabetic(count);
		return randString;		
	}
	
	public String randomNumberGenerate(int count) {
		String randNum = RandomStringUtils.randomNumeric(count);
		return randNum;		
	}
	
	public String randomAlphaNumericGenerate(int count) {
		String randString = RandomStringUtils.randomAlphabetic(count);
		String randNum = RandomStringUtils.randomNumeric(count);
		return randString+randNum;		
	}
	
	public String captureScreen (String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		
		File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;

	}
	
}
