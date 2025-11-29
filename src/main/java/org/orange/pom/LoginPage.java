
package org.orange.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orange.hrm.base.BaseTest;

public class LoginPage {
	 WebDriver driver;
	WebDriverWait wait;
	//	private By byUserName=By.cssSelector("input[placeholder='Username']");
	// private By byUserName=By.xpath("//input[@name='username']");
	// private By byPassword=By.cssSelector("input[name='password'][placeholder='Password']");
	// private By bySubmit=By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button");
	private By byUserName = By.cssSelector("input[placeholder='Username']");
	private By byPassword = By.cssSelector("input[placeholder='Password']");
	private By bySubmit   = By.cssSelector("button[type='submit']");
	private By byLoginError=By.xpath("//p[normalize-space()='Invalid credentials']");
	private By byLoginUserName=By.cssSelector(".oxd-userdropdown-name");
	private By byDashboard=By.cssSelector(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
	private By byLoginRequired=By.xpath("//input[@placeholder='Username']/following::span[text()='Required']");
	private By byPasswordRequired=By.xpath("//input[@placeholder='Password']/following::span[text()='Required']");
	private By byUserDropdown=By.cssSelector(".oxd-icon.bi-caret-down-fill.oxd-userdropdown-icon");
	private By byLogOut=By.xpath("//a[normalize-space()='Logout'][@class='oxd-userdropdown-link']");
	private By byLoginPage =By.xpath("//h5[normalize-space()='Login']");
	private By validateforgotpassword=By.xpath("//p[normalize-space()='Forgot your password?']");
	private By validateUsernamefield=By.xpath("//label[normalize-space()='Username']");
	private By validatePasswordfield=By.xpath("//label[normalize-space()='Password']");
	public LoginPage() {
		this.driver=BaseTest.getdriver();
		this.wait=new WebDriverWait(BaseTest.getdriver(), Duration.ofSeconds(30));
		// TODO Auto-generated constructor stub
	}
public WebElement verifyLoginPage() {
	return wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginPage));
}
	public void login(String username, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#app")));
		WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(byUserName));
		WebElement passward = wait.until(ExpectedConditions.visibilityOfElementLocated(byPassword));
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(bySubmit));
		JavascriptExecutor js= ((JavascriptExecutor)BaseTest.getdriver());
		js.executeScript("arguments[0].value='';", user);
		js.executeScript("arguments[0].value='';", passward);
		user.clear();
		passward.clear();
		user.sendKeys(Keys.CONTROL+"a",Keys.DELETE);
		passward.sendKeys(Keys.CONTROL+"a",Keys.DELETE);
		user.sendKeys(username);
		passward.sendKeys(password);
		submitButton.click();
	}
	public WebElement loginError() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginError));
	}
	public WebElement loginUserRequired() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginRequired));
	}
	public WebElement loginPassRequired() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(byPasswordRequired));
	}
	public WebElement loginSuccess() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(byDashboard));
		
	}
	public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(byUserDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(byLogOut)).click();

	}
	public WebElement loginUserNameValid() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(validateUsernamefield));	
	}
	public String forgotpasswordColour() {
	   String cssValue = wait.until(ExpectedConditions.visibilityOfElementLocated(validateforgotpassword)).getCssValue("color");
	   return cssValue;
	}
	public WebElement loginPasswordNameValid() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(validateUsernamefield));	

	}
	public WebElement submitButton() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(bySubmit));
	}
	

}
