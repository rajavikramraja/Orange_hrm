package org.orange.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateEmployeePage {
	WebDriver driver;
	WebDriverWait wait;
	private By VerifyPersonal=By.xpath("(//h6[normalize-space()='Personal Details'])[1]");
	private By UpdateFirstName=By.xpath("//input[@placeholder='First Name']");
	
	public UpdateEmployeePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public String VerifyUpdatePage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(VerifyPersonal)).getText();
	}
	public String VerifyFirstNmae() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(UpdateFirstName)).getAttribute("value");
	}
}
