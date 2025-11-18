package org.orange.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;;

public class AddEmployeePage {
	WebDriver driver;
	WebDriverWait wait;

	private By 	byFirstNameField=By.cssSelector("input.oxd-input.oxd-input--active.orangehrm-firstname");
	private By byMiddleNameField=By.cssSelector("input.oxd-input.oxd-input--active.orangehrm-middlename");
	private By byLastNameField=By.cssSelector("input.oxd-input.oxd-input--active.orangehrm-lastname");
	private By byEmployeeId=By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
	private By byCreateLogin=By.cssSelector(".oxd-switch-input.oxd-switch-input--active.--label-right");
	// private By byCreateLogin=By.cssSelector("input[type='checkbox']");
	private By byUsernameField=By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div/descendant::input");
	// private By byStatusEnabled=By.xpath("//label[normalize-space()='Enabled']");
	// private By byStatusDisabled=By.xpath("//label[normalize-space()='Disabled']");
//	private By byStatusEnabled=By.cssSelector("input[type='radio'][value='1']");
//	private By byStatusDisabled=By.cssSelector("input[type='radio'][value='2']");
	private By byStatusEnabled=By.xpath("//label[normalize-space()='Enabled']//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']");
	private By byStatusDisabled=By.xpath("//label[normalize-space()='Disabled']//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']");
	
	private By byPassword=By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::div/child::input");
	private By byConformPassword=By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div/child::input");
	private By bySaveButton=By.xpath("//button[normalize-space()='Save']");
	private By byCancelButton=By.xpath("//button[normalize-space()='Cancel']");
	private By byImageButton=By.xpath("//i[@class='oxd-icon bi-plus']/parent::button");
	private By byImageInput=By.cssSelector("input[type='file']");
	private By byimageerror=By.cssSelector("span.oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message");
	private By byImagePath=By.cssSelector("img.employee-image");

	public AddEmployeePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	public void addMandatoryDetails(String FirstName,String MiddleName,String LastName, String EmployeeId ) {
		WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(byFirstNameField));
		WebElement middleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(byMiddleNameField));
		WebElement lastNameFiled = wait.until(ExpectedConditions.visibilityOfElementLocated(byLastNameField));
		WebElement employeeId = wait.until(ExpectedConditions.visibilityOfElementLocated(byEmployeeId));

		firstNameField.clear();
		firstNameField.sendKeys(FirstName);
		middleNameField.clear();
		middleNameField.sendKeys(MiddleName);
		lastNameFiled.clear();
		lastNameFiled.sendKeys(LastName);
		employeeId.clear();
		employeeId.sendKeys(Keys.CONTROL + "a" , Keys.DELETE);
		employeeId.sendKeys(EmployeeId);


	}
	public void imageUpload(String Imagepath ) {
	//	WebElement ImagePath = wait.until(ExpectedConditions.elementToBeClickable(byImageButton));
		WebElement ImageInput = wait.until(ExpectedConditions.presenceOfElementLocated(byImageInput));
	//	ImagePath.click();
		ImageInput.sendKeys(Imagepath);
	}

	public String imageError() {
		String imageErrorMsg = wait.until(ExpectedConditions.presenceOfElementLocated(byimageerror)).getText();
		return imageErrorMsg;
	}
	public String imageUploadSuccess() {
		WebElement image = wait.until(ExpectedConditions.presenceOfElementLocated(byImagePath));
		String  imagescr= image.getAttribute("src");
		return imagescr;
	}
	public boolean createToggleSelect(String createLogin) {
		boolean selected=false;
		if (createLogin.contains("yes")) {
			WebElement createToggle = wait.until(ExpectedConditions.elementToBeClickable(byCreateLogin));
			createToggle.click();
			 selected = true;
		} else {
			System.out.println("Skipping 'Create Login Details' toggle");
		}
		
		return selected;
	}
	public void createLoginDetails(String UserName, String Password, String ConformPassword) {

		WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(byUsernameField));
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(byPassword));
		WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(byConformPassword));
		userNameField.clear();
		userNameField.sendKeys(UserName);
		passwordField.clear();
		passwordField.sendKeys(Password);
		confirmPasswordField.clear();
		confirmPasswordField.sendKeys(ConformPassword);
	}

	public boolean StatusRatio(String Status) {
		boolean selected=true;
		if (Status.toLowerCase().contains("no")) {
			WebElement statusDisable = wait.until(ExpectedConditions.elementToBeClickable(byStatusDisabled));
			statusDisable.click();
		selected=false;
		} else {
			WebElement statusEnable = wait.until(ExpectedConditions.elementToBeClickable(byStatusEnabled));
			statusEnable.click();
			
			
		}
		
		return selected;
		}
//	public boolean disabledStatus() {
//		WebElement statusDisable = wait.until(ExpectedConditions.elementToBeClickable(byStatusDisabled));
//		statusDisable.click();
//		return statusDisable.isSelected();
//
//	}

	public void saveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(bySaveButton)).click();
	}
	public void cancelButton() {
		wait.until(ExpectedConditions.elementToBeClickable(byCancelButton)).click();
	}
}
