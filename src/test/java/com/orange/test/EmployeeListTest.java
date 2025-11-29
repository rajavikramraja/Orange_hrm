package com.orange.test;

import static org.testng.Assert.assertTrue;

import org.orange.pom.PIPPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.hrm.base.BaseTest;
import com.orange.hrm.util.ConfigPropertiess;

public class EmployeeListTest extends BaseTest {
	PIPPage pip;

	@DataProvider(name = "AddEmployeeDatas")
	public Object[][] getEmployeeData() {
		return ConfigPropertiess.getData("AddEmployeeData");

	}

	public void navigateEmployeeList() {
		pip = new PIPPage();
		superLoginmethod();
		pip.PIPView();
	}

	@Test(priority = 2, dataProvider = "AddEmployeeData", invocationCount = 2, dependsOnMethods = "addEmployeeAdmin")
	public void validEmployeeList(String Testcase, String Description, String FirstName, String MiddleName,
			String LastName, String EmployeeId, String createLogin, String username, String password,
			String confirmPassword, String status, String imagePath, String ExpectedResult, String Type)
			throws InterruptedException {

		navigateEmployeeList();
		String actId = null;
		String actfirstMiddleName = null;
		String actLastName = null;
		if (!Type.equals("Positive")) {
			System.out.println("This Negative testcase" + Testcase);
			return;
		}
		String expId = EmployeeId;
		String expfirstMiddleName = FirstName + " " + MiddleName;
		String expLastName = LastName;
		boolean matchid = false;
		boolean matchFirstMiddle = false;
		boolean matchLast = false;
		System.out.println(expId + expfirstMiddleName + expLastName);

		Object[][] recordList = pip.recordList();
		for (Object[] objects : recordList) {
			actId = objects[0].toString();
			actfirstMiddleName = objects[1].toString();
			actLastName = objects[2].toString();
			System.out.println(actId + actfirstMiddleName + actLastName);

			if (expId.equals(actId)) {
				matchid = true;
				System.out.println("Id is found" + actId);
				if (expfirstMiddleName.equals(actfirstMiddleName)) {
					matchFirstMiddle = true;
					System.out.println("First & Middle is found" + actfirstMiddleName);
					if (expLastName.equals(actLastName)) {
						matchLast = true;
						System.out.println("Id is found" + actLastName);
						break;
					}
				}
			}
		}
		assertTrue(matchid && matchFirstMiddle && matchLast, "Employee not add sucessfully");
		Thread.sleep(300);
		logout();
	}
}
