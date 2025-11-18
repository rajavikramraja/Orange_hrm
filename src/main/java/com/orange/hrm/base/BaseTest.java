package com.orange.hrm.base;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orange.hrm.util.*;

import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseTest {
	protected WebDriver driver; 
Properties prop;
String browsered;
String url;
WebDriverWait waits;

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
	    driver = new ChromeDriver(options);
	    break;		
			
	case "firefox":
		FirefoxOptions optionsf = new FirefoxOptions();
		optionsf.addPreference("signon.rememberSignons", false);
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver(optionsf);
			break;
	case "edge":
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
			break;

	default:
		throw new IllegalArgumentException("Invalid browser name: " + browsered);
		
	}
	if (driver == null) {
	    throw new IllegalStateException("WebDriver not initialized. Check browser name: " + browsered);
	}
	driver.manage().window().maximize();
	driver.get(url);
	waitForPageLoad();

	driver.navigate().refresh();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	//System.out.println(driver.getCurrentUrl());
	//System.out.println(driver.getPageSource()+driver.getTitle());

}
	@AfterClass(alwaysRun = true)
	public void tear() {
		 if (driver != null) {
		        driver.quit();
		        driver = null;
		    }
	}
	public void waitForPageLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(30))
	    .until((WebDriver wd) -> ((JavascriptExecutor) wd)
	    .executeScript("return document.readyState").equals("complete"));
	}
	public static String takeScreenShot(WebDriver driver,String Path) {
		  File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 String path =(System.getProperty("user.dir")+"//"+Path+".png");
		 File FinalImage=new File(path);
		  try {
			FileUtils.copyFile(scr, FinalImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return path;
	}
	
	
//	@BeforeMethod
//	public void loginmethod() {
//		LoginPage logins=new LoginPage(driver);
//		logins.login("Vikram", "Vikram@123");
//	}
//	
//	@AfterMethod(alwaysRun = true)
//	public void logout() {
//		LoginPage logino=new LoginPage(driver);
//		logino.logout();
//	}
	@Test
	public void popupclose() throws InterruptedException {
		Thread.sleep(30);
		waits=new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
		WebElement closeButton = driver.findElement(By.xpath("//span[normalize-space()='✕']"));
		closeButton.click();}
		catch (Exception e) {
			System.out.println("Close POPup not show");// TODO: handle exception
		}
		Thread.sleep(30);
		WebElement searchInput = driver.findElement(By.cssSelector("input[placeholder='Search for Products, Brands and More']"));
		searchInput.sendKeys("iphones");
		Thread.sleep(30);
		// List<WebElement> iphoneList = driver.findElements(By.cssSelector(".li._3D0G9a a.oleBil"));
		List<WebElement> iphoneList = driver.findElements(By.xpath("//li[@class='_3D0G9a']"));
		waits.until(ExpectedConditions.visibilityOfAllElements(iphoneList));
		System.out.println("Phone find: " + iphoneList.size());
		for (WebElement lists : iphoneList) {
			System.out.println("hello");
			String text=lists.getText();
			try {
				System.out.println("Phone List :" + text);
			} catch (Exception e) {
				System.out.println("Not found");
				// TODO: handle exception
			}
			
			if (text.contains("iphone 17 pro")) {
				waits.until(ExpectedConditions.elementToBeClickable(lists));
				lists.click();
				break;
				
			}
			else
				System.out.println("Not contain");
			
		}
		String textValid = driver.findElement(By.xpath("//span[normalize-space()='iphone 17 pro']")).getText();
		assertTrue(textValid.contains("iphone 17 pro"));
		
		
		// TODO Auto-generated method stub

	}
}