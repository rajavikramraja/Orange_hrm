package com.orange.hrm.base;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.orange.pom.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orange.hrm.util.*;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.orange.hrm.util.ListenerUtil.class)
public abstract class BaseTest {
	protected static ThreadLocal<WebDriver> driver	=new ThreadLocal<>();
  Properties prop;

String browsered;
String url;
WebDriverWait waits;

public static WebDriver getdriver() {
	return driver.get();
}

	@BeforeClass
	@Parameters("browser")
public void setup(@Optional("") String xmlbrowser) {
	prop=ConfigPropertiess.initProperty();
	String configbrowser=prop.getProperty("browsers","chrome").toLowerCase();
	String mode = prop.getProperty("mode", "normal").toLowerCase();
	 url=prop.getProperty("baseUrl");
	if (mode.equals("cross") && xmlbrowser != null && !xmlbrowser.trim().isEmpty()) {
		browsered=xmlbrowser.toLowerCase().trim();
	} else {
		browsered=configbrowser;
	}
	System.out.println("Execution Mode: " + mode + " | Browser: " + browsered);

	
	switch (browsered.toLowerCase()) {
	case "chrome":
	Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    prefs.put("profile.default_content_setting_values.notifications", 2); // Disable notification popups

	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("prefs", prefs);

	    // Optional: start in incognito to ensure no saved state interferes
	    options.addArguments("--incognito");
	    options.addArguments("--disable-save-password-bubble");
	    options.addArguments("--disable-infobars");
	    options.addArguments("--disable-notifications");

	    WebDriverManager.chromedriver().setup();
	   
	  driver.set(new ChromeDriver(options));
	    break;		
			
	case "firefox":
		FirefoxOptions optionsf = new FirefoxOptions();
		optionsf.addPreference("signon.rememberSignons", false);
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver(optionsf));
			break;
	case "edge":
		WebDriverManager.edgedriver().setup();
		driver.set(new EdgeDriver());
			break;

	default:
		throw new IllegalArgumentException("Invalid browser name: " + browsered);
		
	}
	if (getdriver() == null) {
	    throw new IllegalStateException("WebDriver not initialized. Check browser name: " + browsered);
	}
	getdriver().manage().window().maximize();
	getdriver().get(url);
	waitForPageLoad();

	getdriver().navigate().refresh();
	getdriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

}
	@AfterClass(alwaysRun = true)
	public void tear() {
		 if (getdriver() != null) {
			 getdriver().quit();
		        driver.remove();
		    }
	}
	public void waitForPageLoad() {
		new WebDriverWait(getdriver(), Duration.ofSeconds(30))
	    .until((WebDriver wd) -> ((JavascriptExecutor) wd)
	    .executeScript("return document.readyState").equals("complete"));
	}
	public static String takeScreenShot(String Path) {
		  File scr=((TakesScreenshot)getdriver()).getScreenshotAs(OutputType.FILE);
		  String dates = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		 String path =(System.getProperty("user.dir")+"//Screenshot//"+Path+"_"+dates+".png");
		 
		 File FinalImage=new File(path);
		  try {
			FileUtils.copyFile(scr, FinalImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return path;
	}
	
	
	
	public void superLoginmethod() {
		 String username = prop.getProperty("userName");
		 String password = prop.getProperty("password");
		LoginPage logins=new LoginPage();
		logins.login(username, password);
	} 
	public  void empLoginmethod() {
		String empusername = prop.getProperty("empUserName");
		 String emppassword = prop.getProperty("empPassword");
		LoginPage logins=new LoginPage();
		logins.login(empusername, emppassword);
	} 
	
	
	public  void logout() {
		LoginPage logino=new LoginPage();
		logino.logout();
	}
	}