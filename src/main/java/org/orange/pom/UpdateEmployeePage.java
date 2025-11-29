package org.orange.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.orange.hrm.base.BaseTest;
import com.orange.hrm.base.ExplicityWait;

public class UpdateEmployeePage {
	WebDriver driver;
	// WebDriverWait wait;
	private By verifyPersonal = By.xpath("(//h6[normalize-space()='Personal Details'])[1]");
	private By updFirstName = By.xpath("//input[@placeholder='First Name']");
	private By updMiddleName = By.cssSelector("input[placeholder='Middle Name']");
	private By updLastName = By.cssSelector("input[placeholder='Last Name']");
	private By updEmpId = By
			.xpath("//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By updOtherId = By
			.xpath("//label[normalize-space()='Other Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By updDriverLicense = By.xpath(
			"//label[normalize-space()=\\\"Driver's License Number\\\"]/ancestor::div[contains(@class,'oxd-input-group')]//input");
	// private By updLicenseExp=By.xpath("//label[normalize-space()='License Expiry
	// Date']");
	private By updLicenseExpDateInp = By.xpath(
			"//label[normalize-space()='License Expiry Date']/ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By updValidNationality = By.xpath("//label[normalize-space()='Nationality']");
	private By updNationalDropdown = By.xpath(
			"//label[normalize-space()='Nationality']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-option')]//span");
	private By updValidMartial = By.xpath("//label[normalize-space()='Marital Status']");
	private By updMartialDropdown = By.xpath(
			"//label[normalize-space()='Marital Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@role,'listbox')]//div//span");
	private By updValidDOB = By.xpath("//label[normalize-space()='Date of Birth']");
	private By updDOBInp = By.xpath(
			"//label[normalize-space()='Date of Birth']//ancestor::div[contains(@class,'oxd-input-group')]//input");
	private By updDOBCal = By
			.xpath("//label[normalize-space()='Date of Birth']//ancestor::div[contains(@class,'oxd-input-group')]//i");
	private By updDOBCalMonClick = By.xpath("//li[contains(@class,'oxd-calendar-selector-month')]//i");
	private By updDOBCalMonDropdown = By.xpath(
			"//div[@class='oxd-calendar-selector-month-selected']//following-sibling::ul[contains(@class,'oxd-calendar-dropdown')]//li");
	private By updDOBCalYearDropdown = By.xpath(
			"//div[@class='oxd-calendar-selector-year-selected']//following-sibling::ul[contains(@class,'oxd-calendar-dropdown')]//li");
	private By updDOBCalYearClick = By.xpath("//li[contains(@class,'oxd-calendar-selector-year')]//i");
	private By updDOBCalDate = By.xpath("//div[contains(@class,'oxd-calendar-date-wrapper')]//div");
	private By updDOBCalToday = By.xpath("//div[normalize-space()='Today']");
	private By updDOBCalClear = By.xpath("//div[normalize-space()='Clear']");
	private By updDOBCalClose = By.xpath("//div[normalize-space()='Close']");
	private By updValidGender = By.xpath("//label[normalize-space()='Gender']");
	private By updMaleRatio = By.xpath("//label[normalize-space()='Male']//input");
	private By updFemaleRatio = By.xpath("//label[normalize-space()='Female']//input");
	private By updPersonSave = By.xpath("(//button[normalize-space()='Save'])[1]");

	// =======================
	// Constructor
	// =======================

	public UpdateEmployeePage() {
		// TODO Auto-generated constructor stub
		this.driver = BaseTest.getdriver();
		// this.wait=new WebDriverWait(BaseTest.getdriver(), Duration.ofSeconds(30));
	}
	// =======================
	// Verification Methods
	// =======================

	public WebElement verifyUpdatePage() {
		return ExplicityWait.visibleByLocator(verifyPersonal);
	}

	public WebElement verifyFirstName() {
		return ExplicityWait.visibleByLocator(updFirstName);
	}

	public WebElement verifyMiddleName() {
		return ExplicityWait.visibleByLocator(updMiddleName);
	}

	public WebElement verifyLastName() {
		return ExplicityWait.visibleByLocator(updLastName);
	}

	public WebElement verifyEmpId() {
		return ExplicityWait.visibleByLocator(updEmpId);
	}

	public WebElement verifyOtherID() {
		return ExplicityWait.visibleByLocator(updOtherId);
	}

	public WebElement verifyDriverLicense() {
		return ExplicityWait.visibleByLocator(updDriverLicense);
	}

	public WebElement verifyLicenseExpDate() {
		return ExplicityWait.visibleByLocator(updLicenseExpDateInp);
	}

	public WebElement verifyNationality() {
		return ExplicityWait.visibleByLocator(updValidNationality);
	}

	public WebElement verifyMaritalStatus() {
		return ExplicityWait.visibleByLocator(updValidMartial);
	}

	public WebElement verifyDOB() {
		return ExplicityWait.visibleByLocator(updValidDOB);
	}

	public WebElement verifyGender() {
		return ExplicityWait.visibleByLocator(updValidGender);
	}

	public WebElement verifyPersonSaveButton() {
		return ExplicityWait.visibleByLocator(updPersonSave);
	}

	// =======================
	// Dropdown & Calender Methods
	// =======================
	public List<WebElement> nationalityDropdown() {
		return ExplicityWait.visibleAllByLocator(updNationalDropdown);
	}

	public List<WebElement> maritalDropdown() {
		return ExplicityWait.visibleAllByLocator(updMartialDropdown);
	}

	public WebElement ValidPersonSaveButton() {
		return ExplicityWait.clickByLocator(updPersonSave);
	}

	public void selectMonth(String Month) {
		ExplicityWait.clickByLocator(updDOBCalMonClick).click();
		List<WebElement> monthOptionElement = ExplicityWait.visibleAllByLocator(updDOBCalMonDropdown);
		for (WebElement monthOption : monthOptionElement) {
			if (monthOption.getText().trim().equalsIgnoreCase(Month)) {
				monthOption.click();
				break;
			}
		}
	}

	public void selectYear(String Year) {
		ExplicityWait.clickByLocator(updDOBCalYearClick).click();
		List<WebElement> yearOptionElement = ExplicityWait.visibleAllByLocator(updDOBCalYearDropdown);
		for (WebElement yearOption : yearOptionElement) {
			if (yearOption.getText().trim().equalsIgnoreCase(Year)) {
				yearOption.click();
				break;
			}
		}
	}

	public void selectDate(String Date) {

		List<WebElement> dateOptionElement = ExplicityWait.visibleAllByLocator(updDOBCalDate);
		for (WebElement dateOption : dateOptionElement) {
			if (dateOption.getText().trim().equalsIgnoreCase(Date)) {
				dateOption.click();
				break;
			}
		}
	}

	// =======================
	// Action Methods
	// =======================

	public void clearAndType(WebElement ele, String value) {
		ele.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		ele.sendKeys(value);
	}

	public void enterFirstName(String firstName) {
		clearAndType(verifyFirstName(), firstName);
	}

	public void enterdMiddleName(String middleName) {
		clearAndType(verifyMiddleName(), middleName);
	}

	public void enterdLastName(String lastName) {
		clearAndType(verifyLastName(), lastName);
	}

	public void enterEmpId(String empId) {
		clearAndType(verifyEmpId(), empId);
	}

	public void enterOtherId(String otherId) {
		clearAndType(verifyOtherID(), otherId);
	}

	public void enterDriverLic(String license) {
		clearAndType(verifyDriverLicense(), license);
	}

	public void enterLicenseExpDate(String LicenseDateyyyymmdd) {
		clearAndType(verifyLicenseExpDate(), LicenseDateyyyymmdd);
	}

	public void selectNationality(String national) {
		List<WebElement> nationalityDropdown = nationalityDropdown();
		for (WebElement nationElement : nationalityDropdown) {
			String nationValue = nationElement.getText().trim();
			if (nationValue.equalsIgnoreCase(national)) {
				nationElement.click();
				break;
			}
		}
	}

	public void selectMarital(String Marital) {
		List<WebElement> maritalDropdown = maritalDropdown();
		for (WebElement maritalElement : maritalDropdown) {
			String MaritalValue = maritalElement.getText().trim();
			if (MaritalValue.equalsIgnoreCase(Marital)) {
				maritalElement.click();
				break;
			}
		}
	}

	public void selectDOB(String Date, String Month, String Year) {
		ExplicityWait.clickByLocator(updDOBCal).click();
		selectMonth(Month);
		selectYear(Year);
		selectDate(Date);
	}

	public void clickPersonalSaveButton() {
		ValidPersonSaveButton().click();
	}

	public void UpdateMandatoryEmployee(String FirstName, String LastName) {
		enterFirstName(FirstName);
		enterdLastName(LastName);
	}
}
