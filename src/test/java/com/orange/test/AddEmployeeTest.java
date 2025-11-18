package com.orange.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.openqa.selenium.TimeoutException;
import org.orange.pom.AddEmployeePage;
import org.orange.pom.PIPPage;
import org.orange.pom.UpdateEmployeePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.hrm.base.BaseTest;
import com.orange.hrm.util.ConfigPropertiess;

public class AddEmployeeTest extends BaseTest {

	@DataProvider(name="AddEmployeeData")
	public Object[][] getEmployeeData() {
		return ConfigPropertiess.getData("AddEmployeeData");

	}
	@Test(dataProvider = "AddEmployeeData",priority = 1,description = "PIP Add Employee Data")
	public void addEmployeeAdmin(String Testcase,String Description,String FirstName,String	MiddleName,String LastName,String EmployeeId,
			String createLogin,String username,String password,	String confirmPassword,
			String status, String imagePath,String ExpectedResult, String Type) {
		PIPPage pip=new PIPPage(driver);
		AddEmployeePage addEmployee=new AddEmployeePage(driver);
		UpdateEmployeePage updateEmployee=new UpdateEmployeePage(driver);

		System.out.println(Description);
		pip.PIPView();	
		//System.out.println("pip view");
	//	System.out.println(driver.getCurrentUrl());
		pip.PIPAdd();
		addEmployee.addMandatoryDetails(FirstName, MiddleName, LastName, EmployeeId);
		try {
			if (!imagePath.isBlank()) {
				String imgs = System.getProperty("user.dir") + imagePath;
				//System.out.println("Uploading image: " + imgs);
				addEmployee.imageUpload(imgs);

				// Wait briefly for upload
				Thread.sleep(2000);

				// Try checking for success first
				if (addEmployee.imageUploadSuccess().contains("data:image")) {
					System.out.println("✅ Image uploaded successfully");
				} else {
					// If not success, check if any error message exists
					try {
						String imageError = addEmployee.imageError();
						System.out.println("⚠️ Image upload failed: " + imageError);
					} catch (TimeoutException e) {
						System.out.println("⚠️ Neither image nor error message found");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ File upload process failed");
		}


		if (addEmployee.createToggleSelect(createLogin)) {
			addEmployee.createLoginDetails(username, password, confirmPassword);

			addEmployee.StatusRatio(status);

			
		} 
//		else {
//			addEmployee.saveButton();
//			assertTrue(updateEmployee.VerifyUpdatePage().contains("Personal Details"));
//		}

		addEmployee.saveButton();
		if (Type.contains("Positive")) {
			//assertTrue(updateEmployee.VerifyUpdatePage().contains("Personal Details"));	
			assertEquals(FirstName, updateEmployee.VerifyFirstNmae(), "First Name not save Properly");
			System.out.println("Save properly with verify fist name");
		}else {
		System.out.println("Save properly without verify fist name");
		}
	}

}
//// System.out.println("Testcase"+Testcase+"Description"+Description+"FirstName"+FirstName+"MiddleName"+MiddleName+"LastName"
//+LastName+"EmployeeId"+EmployeeId+"createLogin"+createLogin+"username"+username+"password"+password+"confirmPassword"+confirmPassword
//		+"status"+status+"imagePath"+imagePath+"ExpectedResult"+ExpectedResult+"Type"+Type);
