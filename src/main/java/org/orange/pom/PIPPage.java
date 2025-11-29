
package org.orange.pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orange.hrm.base.BaseTest;

public class PIPPage {
	WebDriver driver;
	WebDriverWait wait;

	// private By
	// PIPMenuButton=By.xpath("//span[normalize-space()='PIM'][@class='oxd-text
	// oxd-text--span oxd-main-menu-item--name']/parent::a");
	private By PIPMenuButton = By.xpath("//span[normalize-space()='PIM']/ancestor::a");

	private By PIPMenuNavagigate = By.cssSelector(".oxd-text.oxd-text--h5.oxd-table-filter-title");
	private By PIPAddButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By PIPCreateLoginButton = By.cssSelector("span.oxd-switch-input.oxd-switch-input--active.--label-right");
	private By ByRecordRow = By.cssSelector("div[class=oxd-table-card]");
	private By ByHeader = By.cssSelector(".oxd-table-header-cell.oxd-padding-cell.oxd-table-th");
	private By ByRecordCell = By.cssSelector("div[class='oxd-table-cell oxd-padding-cell']");

	public PIPPage() {
		// TODO Auto-generated constructor stub
		this.driver = BaseTest.getdriver();
		this.wait = new WebDriverWait(BaseTest.getdriver(), Duration.ofSeconds(30));
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

	public Object[][] recordList() {

		List<WebElement> EmpRow = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByRecordRow));
		List<WebElement> EmpHeader = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByHeader));
		JavascriptExecutor js = (JavascriptExecutor) BaseTest.getdriver();
		js.executeScript("arguments[0].scrollIntoView(true);", EmpRow.get(0));
		// System.out.println(EmpRow.size()+"a"+EmpHeader.size());
		Object[][] DataRows = new Object[EmpRow.size()][EmpHeader.size() - 2];
		for (int i = 0; i < EmpRow.size(); i++) {
			List<WebElement> EmpCell = EmpRow.get(i).findElements(ByRecordCell);
			for (int j = 1; j < EmpHeader.size() - 1; j++) {
				DataRows[i][j - 1] = EmpCell.get(j).getText();

			}
		}
		// System.out.println(Arrays.deepToString(DataRows));
		return DataRows;
	}

	public void selectEdit(int row) {
		List<WebElement> EmpRow = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByRecordRow));
		List<WebElement> EmpHeader = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByHeader));
		JavascriptExecutor js = (JavascriptExecutor) BaseTest.getdriver();
		js.executeScript("arguments[0].scrollIntoView(true);", EmpRow.get(0));

		WebElement empcell = EmpRow.get(row);

		empcell.findElements(ByHeader).get(7).click();
	}

}
