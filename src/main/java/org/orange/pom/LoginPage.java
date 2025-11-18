
package org.orange.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		// TODO Auto-generated constructor stub
	}

	public void login(String username, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#app")));
		WebElement user = wait.until(ExpectedConditions.visibilityOfElementLocated(byUserName));
		WebElement passward = wait.until(ExpectedConditions.visibilityOfElementLocated(byPassword));
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(bySubmit));
		user.clear();
		user.sendKeys(username);
		passward.clear();
		passward.sendKeys(password);
		submitButton.click();
	}
	public String loginError() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginError)).getText();
	}
	public String loginUserRequired() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginRequired)).getText();
	}
	public String loginPassRequired() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(byPasswordRequired)).getText();
	}
	public boolean loginSuccess() {
		try {
			Thread.sleep(3000);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(byDashboard)).isDisplayed();
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}
	public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(byUserDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(byLogOut)).click();

	}
	public String loginUserNameValid() {
		return wait.until(ExpectedConditions.elementToBeClickable(byLoginUserName)).getText();	
	}

}
