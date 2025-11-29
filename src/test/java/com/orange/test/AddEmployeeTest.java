package com.orange.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.orange.pom.AddEmployeePage;
import org.orange.pom.PIPPage;
import org.orange.pom.UpdateEmployeePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orange.hrm.base.BaseTest;

public class AddEmployeeTest extends BaseTest {

	@Test(dataProvider = "AddEmployeeDatas", dataProviderClass = EmployeeListTest.class)
	public void loginExcel(String Testcase, String Description, String FirstName, String MiddleName, String LastName,
			String EmployeeId, String createLogin, String username, String password, String confirmPassword,
			String status, String imagePath, String ExpectedResult, String Type) throws InterruptedException {
		superLoginmethod();
		PIPPage pip = new PIPPage();
		AddEmployeePage addemp = new AddEmployeePage();
		UpdateEmployeePage updemp = new UpdateEmployeePage();
		pip.PIPView();
		pip.PIPAdd();
		// Mandatory field add
		addemp.addMandatoryDetails(FirstName, MiddleName, LastName, EmployeeId);

		// Image validate
		if (imagePath != null && !imagePath.isBlank()) {
			String imgs = System.getProperty("user.dir") + imagePath;
			addemp.imageUpload(imgs);
			Thread.sleep(3000);
			assertTrue(addemp.imageUploadSuccess().contains("data:image"), "Image should be Upload");
			Assert.assertFalse(addemp.imageError().isDisplayed(), "Image should show error");
		}
		if (createLogin.equalsIgnoreCase("yes")) {
			addemp.createToggleSelect("yes");
			addemp.createLoginDetails(username, password, confirmPassword);
			addemp.StatusRatio(status);

		}
		addemp.saveButton();
		Thread.sleep(2000);
		boolean saved = false;
		try {
			saved = updemp.verifyUpdatePage().isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
			saved = false;
		}
		if (Type.equalsIgnoreCase("Positive")) {
			assertTrue(saved, "Expected to navigate to Personal Details page but it did not.");
			assertEquals(updemp.verifyFirstName().getAttribute("value"), FirstName, "First name not saved correctly");
			System.out.println("Employee save case sucessfully " + Testcase);
		} else {
			assertFalse(saved, "Should NOT navigate to Personal Details for invalid data");
			System.out.println("Negative validation passed " + Testcase);
		}
		logout();
	}

}
