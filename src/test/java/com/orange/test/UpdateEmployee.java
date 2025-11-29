package com.orange.test;

import static org.testng.Assert.assertTrue;

import org.orange.pom.PIPPage;
import org.orange.pom.UpdateEmployeePage;
import org.testng.annotations.Test;

import com.orange.hrm.base.BaseTest;

public class UpdateEmployee extends BaseTest {
	PIPPage pip;

	public void navigateToUpdateemployee() {
		pip = new PIPPage();
		superLoginmethod();
		pip.PIPView();
		pip.selectEdit(1);
//		getdriver().findElement(By.xpath("(//button[@class='oxd-icon-button oxd-table-cell-action-space'])[1]"))
//				.click();
//		Object[][] recordList = pip.recordList();
//		for (Object[] objects : recordList) {
//			String actId = objects[0].toString();
//			if (actId.equals("0002")) {
//
//			}
//		}
	}

	@Test
	public void ValidUpdateEmployeeField() {
		navigateToUpdateemployee();
		UpdateEmployeePage updEmp = new UpdateEmployeePage();
		assertTrue(updEmp.verifyFirstName().isDisplayed(), "First Name field should be display");
		assertTrue(updEmp.verifyMiddleName().isDisplayed(), "Middle Name field should be display");
		assertTrue(updEmp.verifyLastName().isDisplayed(), "Last Name field should be display");
		assertTrue(updEmp.verifyEmpId().isDisplayed(), "Employee Id field should be display");
		assertTrue(updEmp.verifyOtherID().isDisplayed(), "Other ID field should be display");
		assertTrue(updEmp.verifyDriverLicense().isDisplayed(), "Driver License Number field should be display");
		assertTrue(updEmp.verifyLicenseExpDate().isDisplayed(), "License Expiry Date field should be display");
		assertTrue(updEmp.verifyNationality().isDisplayed(), "Nationality dropdown should be display");
		assertTrue(updEmp.verifyMaritalStatus().isDisplayed(), "Marital status dropdown should be display");
		assertTrue(updEmp.verifyDOB().isDisplayed(), "Marital status dropdown should be display");
		assertTrue(updEmp.verifyPersonSaveButton().isEnabled(), "Marital status dropdown should be display");

	}

}
