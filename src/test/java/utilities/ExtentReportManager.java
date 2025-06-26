package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports. ExtentReports;
import com.aventstack.extentreports. ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter. ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration. Theme;

import testBase.BaseCase; 

public class ExtentReportManager implements ITestListener 
{ 
	public ExtentSparkReporter sparkReporter; // UI of the report 
	public ExtentReports extent; //populate common info on the report 
	public ExtentTest test; // creating test case entries in the report and update status of the test methods 
	
	String repName;
	
	public void onStart(ITestContext context) { 
		
		
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
		 * new Date(); String currentDateTimeStamp = df.format(dt);
		 */
		
		
		//Creating extent report the report with dynamic name with timestamp is missing.
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter (".\\reports\\"+repName);

		sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // TiTle of report 
		sparkReporter.config().setReportName("Opencart Functional Testing"); // name of the report 
//		sparkReporter.config().setTheme (Theme.STANDARD); 
		sparkReporter.config().setTheme (Theme.DARK); 
		
		extent=new ExtentReports(); 
		extent.attachReporter (sparkReporter);
		
		//Adding these things dynamically is missing
		
		extent.setSystemInfo("Application", "Opencart"); 
		extent.setSystemInfo("Module", "Admin"); 
		extent.setSystemInfo("Sub Module", "Customer"); 
		extent.setSystemInfo("Username", System.getProperty("user.name")); 
		extent.setSystemInfo("Environment", "QA"); 
		
		String os = context.getCurrentXmlTest().getParameter("OS"); //Getting the parameter from the xml file.
		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("Browser"); //Getting the parameter from the xml file.
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups(); //Getting the included groups from the xml file.
		
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	} 
	public void onTestSuccess (ITestResult result) { 
		test = extent.createTest(result.getClass().getName()); // create a new entry in the report 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed."); // update status p/f/s 
	}
	
	public void onTestFailure (ITestResult result) { 
		test = extent.createTest(result.getTestClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed."); 
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseCase().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	} 
	
	public void onTestSkipped (ITestResult result) { 
		test = extent.createTest(result.getClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+" got skipped."); 
		test.log(Status.INFO, result.getThrowable().getMessage()); 
	} 
	
	public void onFinish (ITestContext context) { 
		extent.flush(); 
		
		
		//This will open the report immediately after finishing the test.
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//This will send a mail by attaching the generated report immediately
		
		/*try { 
			
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
				
			// Create the email message
			
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver (new DataSourceUrlResolver (url));
			email.setHostName("smtp.googlemail.com"); //Only for gmail
			email.setSmtpPort(465);
			email.setAuthenticator (new DefaultAuthenticator("sabatsibananda@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("sabatsibananda@gmail.com"); //Sender
			email.setSubject("Test Results");
			email.setMsg("Please find Attached Report....");
			email.addTo("sibananda.s@somaiya.edu"); //Receiver
			email.attach(url, "extent report", "please check report...");
			email.send(); // send the email
			
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}*/
		
	}
	
}