package org.orange.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIPPage {
	WebDriver driver;
	WebDriverWait wait;
	
	// private By PIPMenuButton=By.xpath("//span[normalize-space()='PIM'][@class='oxd-text oxd-text--span oxd-main-menu-item--name']/parent::a");
	private By PIPMenuButton=By.xpath("//span[normalize-space()='PIM']/ancestor::a");
	
	private By PIPMenuNavagigate=By.cssSelector(".oxd-text.oxd-text--h5.oxd-table-filter-title");
	private By PIPAddButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By PIPCreateLoginButton=By.cssSelector("span.oxd-switch-input.oxd-switch-input--active.--label-right");


	public PIPPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	public void PIPView() {
		WebElement pimButton = wait.until(ExpectedConditions.elementToBeClickable(PIPMenuButton));
		pimButton.click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(PIPMenuNavagigate));
//		wait.until(ExpectedConditions.elementToBeClickable(PIPMenuButton)).click();
//		//return wait.until(ExpectedConditions.visibilityOfElementLocated(PIPMenuNavagigate)).getText();
	}
	public void PIPAdd() {
		wait.until(ExpectedConditions.elementToBeClickable(PIPAddButton)).click();	 

	}

}
