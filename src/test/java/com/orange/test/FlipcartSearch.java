package com.orange.test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.orange.hrm.base.BaseTest;
public class FlipcartSearch  {
	WebDriver driver;
	WebDriverWait wait;
@Test
public void login() throws InterruptedException {
	driver=new ChromeDriver();
	driver.get("https://www.flipkart.com/");
	String windowHandle = driver.getWindowHandle();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

     // Close login popup
     try {
         WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(
             By.xpath("//span[text()='✕']")));
         closePopup.click();
     } catch (Exception e) {
         System.out.println("No popup found");
     }

     // Search box
     WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
         By.cssSelector("input[placeholder='Search for Products, Brands and More']")));
     searchBox.sendKeys("iphone");

     // ✅ Wait for suggestions to appear
     List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
         By.xpath("//li[@class='_3D0G9a']")
     ));

     System.out.println("Suggestions found: " + suggestions.size());
     for (WebElement s : suggestions) {
    	 String text=s.getText().trim();
         System.out.println(text);   
         if (text.contains("iphone 17 pro")) {
        	 s.click();
        	 break;
		}
         else
        	 System.out.println("Item not found");
     }
     WebElement textValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='iphone 17 pro']")));
     String text = textValid.getText();
     assertEquals(text, "iphone 17 pro");
     System.out.println("Working");
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Apple iPhone 17 Pro (Deep Blue, 256 GB)']"))).click();
     Set<String> windowHandles = driver.getWindowHandles();
     for (String string : windowHandles) {
		if (!string.equals(windowHandle)) {
			driver.switchTo().window(string);
			break;
		}
	}
     System.out.println(driver.getTitle()); 

 assertEquals(driver.getTitle(), "Apple iPhone 17 Pro ( 256 GB Storage, 0 GB RAM ) Online at Best Price On Flipkart.com");
 
 WebElement addcart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add to cart']")));
 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", addcart);
		 addcart.click();
		 Thread.sleep(30);
		System.out.println("Final"+driver.getTitle()); 
 assertEquals(driver.getTitle(), "Apple iPhone 17 Pro ( 256 GB Storage, 0 GB RAM ) Online at Best Price On Flipkart.com");
     driver.quit();
 }}
	
	
//	Thread.sleep(30);
//	driver.findElement(By.cssSelector("input[placeholder='Search for Products, Brands and More']")).sendKeys("iphone ");
//	Thread.sleep(30);
//List<WebElement> phoneList = driver.findElements(By.xpath("//ul[@class='_1sFryS _2x2Mmc']//li//div[contains(@class,'YGcVZO')]"));
//wait=new WebDriverWait(driver, Duration.ofSeconds(30));
//
//wait.until(ExpectedConditions.visibilityOfAllElements(phoneList));
//System.out.println(phoneList.size());
//for (WebElement iphone : phoneList) {
//	String phoneValue=iphone.getText();
//	System.out.println(phoneValue);
//	
//}
//	//WebElement closeButton = driver.findElement(By.xpath("//span[normalize-space()='✕']"));
//	//wait.until(ExpectedConditions.)
//	//System.out.println("HI");
//}
//}
