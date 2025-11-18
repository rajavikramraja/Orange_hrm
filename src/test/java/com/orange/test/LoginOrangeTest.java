package com.orange.test;

import static org.testng.Assert.assertEquals;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.orange.pom.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.orange.hrm.base.BaseTest;
import com.orange.hrm.util.ConfigPropertiess;

public class LoginOrangeTest extends BaseTest{
	final String BOLD = "\u001B[1m";
	final String RESET = "\u001B[0m";
@DataProvider(name="LoginProvide")
public Object[][] getDatas() {
	return ConfigPropertiess.getData("LoginData");
	
}

@Test(enabled = false,dataProvider = "LoginProvide",priority = 1,description = "Valid & Invalid Login")
public void Login(String userName,String password,String LoginName ) {
	LoginPage logins=new LoginPage(driver);
	//System.out.println("launch");
	logins.login(userName, password);
	String masked = (password == null || password.isEmpty()) ? "<empty>" : "******";
	if (userName.equals("Vikram") && password.equals("Vikram@123")||
			userName.equals("Rajan") && password.equals("Rajan@123")) {
		assertEquals(logins.loginSuccess() && logins.loginUserNameValid().equalsIgnoreCase(LoginName), true, "Login Succesfull");
		
		System.out.println("\nUsername: " + userName + " Password: "+masked +BOLD +"\nValid UserName and Password login SuccessFully"+RESET);
		logins.logout();
		
	}
	
	else if (userName.isEmpty() && password.isEmpty()) {
		assertTrue(logins.loginPassRequired().equalsIgnoreCase("Required") &&logins.loginUserRequired().equalsIgnoreCase("Required") , "Both Username and password need to fill");
		System.out.println("\nUsername: " + userName + " Password: "+masked +BOLD+ "\n<Required to enter UserName and Password"+RESET);
		//System.out.println("Both Username and password need to fill");
	}
	else if (userName.isEmpty()) {
		assertTrue(logins.loginUserRequired().equalsIgnoreCase("Required") , "Username need to fill");
		System.out.println("\nUsername: " + userName + " Password: "+masked +BOLD+ "\nRequired to enter UserName"+RESET);
		
		//System.out.println("password need to fill");
	}

	else if (password.isEmpty()) {
		assertTrue(logins.loginPassRequired().equalsIgnoreCase("Required") , "password need to fill");
		System.out.println("\nUsername: " + userName + " Password: "+masked + BOLD+"\nRequired to enter Password"+RESET);
		
		//	System.out.println("Username need to fill");
	}
	else 
		assertEquals(logins.loginError().trim(), "Invalid credentials", "Login Unsuccesfull");
	System.out.println("\nUsername: " + userName + " Password: "+masked +BOLD+ "\nInvalid UserName and Password login Unsuccesfull"+RESET);
	
	//assertEquals(logins.loginSuccess(), true, "Login Succesfull");;
	
}
//@Test
//public void Invalid() throws InterruptedException {
//	LoginPage logins=new LoginPage(driver);
//	
//	logins.login("as", "password");
//	System.out.println("launch invalid");
//	System.out.println(driver.getCurrentUrl());
//	String home = driver.getWindowHandle();
//	Thread.sleep(2000);
//	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[3]/div[1]/a[1]")).click();
//	driver.switchTo().newWindow(WindowType.TAB);
//	System.out.println(driver.getCurrentUrl());
//	driver.get("https://www.google.com/");
//	String google = driver.getWindowHandle();
//	System.out.println(driver.getCurrentUrl());
//	driver.switchTo().newWindow(WindowType.TAB);
//	System.out.println(driver.getCurrentUrl());
//	driver.get("https://www.tutorialspoint.com/testng/testng_ignore_test.htm");
//	String tutorial = driver.getWindowHandle();
//	driver.switchTo().window(google);
//	WebElement findElement = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
//	findElement.sendKeys("good");
//	Set<String> windowHandles = driver.getWindowHandles();
//	List<String> windoww=new ArrayList<>(windowHandles);
//	for (String string : windoww) {
//		 driver.switchTo().window(string);
//		    System.out.println("Window URL: " + driver.getCurrentUrl());
//		
//	}
//	driver.switchTo().window(windoww.get(1));
//	System.out.println("final"+driver.getCurrentUrl());
//	
//}
//	
}
