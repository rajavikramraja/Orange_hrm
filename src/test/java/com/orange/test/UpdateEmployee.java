package com.orange.test;

import com.mailslurp.clients.*;
import com.mailslurp.models.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mailslurp.apis.*;

public class UpdateEmployee {
//	WebDriver driver;
//	String emailAddress;
//	String password="Pwd@" + new Random().nextInt(9999);
//	ApiClient client;
//	UUID inboxId;
//	String confirmationCode;
//	Email email;
//	@BeforeSuite
//	public void generateMail() throws ApiException {
//		// install from https://central.sonatype.com/artifact/com.mailslurp/mailslurp-client-java/
//		client = Configuration.getDefaultApiClient();
//		client.setApiKey("034999e958ac328825dd176a9b1d4ecff3440ff02ec36d0ef85be82e1759b764");
//		// create an inbox
//		InboxControllerApi inboxControllerApi = new InboxControllerApi(client);
//		InboxDto inbox = inboxControllerApi.createInboxWithDefaults().execute();
//		inboxId = inbox.getId();
//		emailAddress = inbox.getEmailAddress();
//
//		System.out.println("Temporary email: " + emailAddress + "/nPassword: "+password);
//		WebDriverManager.chromedriver().setup();
//		driver=new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://playground.mailslurp.com/");
//	}
//
//
//	@Test(priority = 1)
//	public void mailValidate() {
//		// driver.get("https://playground.mailslurp.com/");
//		driver.findElement(By.cssSelector("[data-test=sign-in-create-account-link]")).click();
//		System.out.println("mailValidate"+driver.getCurrentUrl());
//		driver.findElement(By.name("email")).sendKeys(emailAddress);
//		driver.findElement(By.name("password")).sendKeys(password);
//
//		// submit the form to trigger the playground's email confirmation process
//		// we will need to receive the confirmation email and extract a code
//		driver.findElement(By.cssSelector("[data-test=sign-up-create-account-button]")).click();
//
//		//driver.findElement(By.id("email")).sendKeys(emailAddress);
//		//		driver.findElement(By.cssSelector("input[placeholder='Enter your username']")).sendKeys(emailAddress);
//		//	    driver.findElement(By.cssSelector("input[placeholder='Enter your password']")).sendKeys("Test@123");
//		//	    driver.findElement(By.cssSelector(".Button__button___vS7Mv")).click();
//	}
//	@Test(dependsOnMethods = "mailValidate",priority = 2)
//	public void canReceiveConfirmationEmail() throws ApiException {
//		WaitForControllerApi waitForController = new WaitForControllerApi(client);
//		email = waitForController.waitForLatestEmail().inboxId(inboxId).timeout((long) 30000).unreadOnly(true).execute();
//
//		System.out.println("Email subject: " + email.getSubject());
//		System.out.println("Email body: " + email.getBody());
//		Assert.assertTrue(email.getSubject().contains("confirm your email address"), "Email subject mismatch!");
//	}
//	@AfterSuite
//	public void pub() {
//		// TODO Auto-generated method stub
//		driver.close();
//	}
//	@Test(dependsOnMethods = "canReceiveConfirmationEmail",priority = 3)
//	public void canExtractConfirmationCodeFromEmail() {
//		// create a regex for matching the code we expect in the email body
//		Pattern p = Pattern.compile(".*verification code is (\\d+).*");
//		Matcher matcher = p.matcher(email.getBody());
//
//		// find first occurrence and extract
//		assertTrue(matcher.find());
//		confirmationCode = matcher.group(1);
//
//		assertTrue(confirmationCode.length() == 6);
//	}
//
//
//	@Test(dependsOnMethods ="canExtractConfirmationCodeFromEmail",priority = 4 )
//	public void canSubmitVerificationCodeToPlayground() throws InterruptedException {
////		System.out.println("canSubmitVerificationCodeToPlayground"+driver.getCurrentUrl());
////
////		driver.findElement(By.name("code")).sendKeys(confirmationCode);
////		driver.findElement(By.cssSelector("[data-test=confirm-sign-up-confirm-button]")).click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    WebElement codeBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("code")));
//	    codeBox.sendKeys(confirmationCode);
//
//	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
//	        By.cssSelector("[data-test=confirm-sign-up-confirm-button]")
//	    ));
//	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
//	    Thread.sleep(2000);
//	    System.out.println("✅ Submitted confirmation code successfully");
//	
//	}
////	@Test(dependsOnMethods = "canSubmitVerificationCodeToPlayground",priority = 5)
////	public void canLoginWithConfirmedUser() {
////		// load the main playground login page
////		//driver.get("https://playground.mailslurp.com/");
////		driver.navigate().refresh();
////		String text = driver.findElement(By.xpath("//span[normalize-space()='Sign in to your account']")).getText();
////		assertEquals( text.trim(), "Sign in to your account","Page in Sign in");
////		System.out.println("canLoginWithConfirmedUser1"+driver.getCurrentUrl()+text);
////
////		// login with now confirmed email address
////		driver.findElement(By.name("username")).sendKeys(emailAddress);
////		driver.findElement(By.name("password")).sendKeys(password);
////		driver.findElement(By.cssSelector("[data-test=sign-in-sign-in-button]")).click();
////		System.out.println("canLoginWithConfirmedUser" + driver.getCurrentUrl());
////		//String text2 = driver.findElement(By.xpath("//span[normalize-space()='Sign in to your account']")).getText();
////		//assertEquals( text.trim(), "Sign in to your account","Page in Sign in");
////		// verify that user can see authenticated content
////		// assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Welcome"));
////		try {
////			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////			WebElement welcomeText = wait.until(
////					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Welcome')]")));
////			Assert.assertTrue(welcomeText.getText().contains("Welcome"), "Login success message not found!");
////		}catch (Exception e) {
////			// TODO: handle exception
////			e.printStackTrace();
////		}}
////
//	@Test(dependsOnMethods = "canSubmitVerificationCodeToPlayground", priority = 5)
//	public void canLoginWithConfirmedUser() {
//	    driver.navigate().refresh();
//	    Assert.assertEquals(
//	        driver.findElement(By.xpath("//span[normalize-space()='Sign in to your account']")).getText().trim(),
//	        "Sign in to your account",
//	        "Page not in Sign-in state!"
//	    );
//
//	    driver.findElement(By.name("username")).sendKeys(emailAddress);
//	    driver.findElement(By.name("password")).sendKeys(password);
//	   // driver.findElement(By.xpath("//button[normalize-space()='Sign In']")).click();
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
//	        By.cssSelector("[data-test=confirm-sign-up-confirm-button]")
//	    ));
//
//	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
//	    System.out.println("✅ Confirmation button clicked successfully");
//	    WebElement welcomeText = wait.until(
//	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Sign Out')]"))
//	    );
//	    Assert.assertTrue(welcomeText.isDisplayed(), "Sign out button not displayed!");
//	
//
//	}
//	@Test
//	public void login() {
//		driver.findElement(By.name("username")).sendKeys("Vikramraja161196@gmail.com");
//		driver.findElement(By.name("password")).sendKeys("Vikram@123");
//		driver.findElement(By.cssSelector("[data-test=sign-in-sign-in-button]")).click();
//		System.out.println("canLoginWithConfirmedUser" + driver.getCurrentUrl());
//		
//	}
//	//	@BeforeMethod
//	//	public void navigateToBaseURL() {
//	//	    
//	//	}
}
