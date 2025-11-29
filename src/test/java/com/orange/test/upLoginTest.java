package com.orange.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.orange.pom.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.hrm.base.BaseTest;
import com.orange.hrm.util.ConfigPropertiess;

public class upLoginTest extends BaseTest{
	@DataProvider
	public Object[][] getLoginData()  {
		return ConfigPropertiess.getDataExcel("LoginData");
	}
	@Test(priority = 2)
	public void forgotPassswordColor() {
		// TODO Auto-generated method stub
		LoginPage login=new LoginPage();
		assertEquals(login.forgotpasswordColour(), "rgba(255, 123, 29, 1)", "Color need to be orange");
		 //System.out.println(login.forgotpasswordColour()); 
	}
	@Test(priority = 1)
	private void fieldsValidated() {
		// TODO Auto-generated method stub
		LoginPage login=new LoginPage();
		assertTrue(login.loginUserNameValid().isDisplayed(), "Username should be display");
		assertTrue(login.loginPasswordNameValid().isDisplayed(), "Password should be display");
		assertTrue(login.submitButton().isEnabled(),"Submit button need to be enabled");
	}
@Test(dataProvider = "getLoginData",priority = 3, enabled = false)
public void loginUsing(String Tc,String UserName,String password, String expectedResult ) {
	// TODO Auto-generated method stub
	LoginPage login=new LoginPage();
	assertTrue(login.verifyLoginPage().isDisplayed(), "Login Page need to navigate");
	login.login(UserName, password);
	System.out.println(Tc+"__"+UserName+"__"+password);
switch (expectedResult) {
case "valid": {
	assertTrue(login.loginSuccess().isDisplayed(), "Dashboard should be displayed for valid login "+ Tc);
	login.logout();
	assertTrue(login.verifyLoginPage().isDisplayed(), "Login Page need to navigate after Logout "+ Tc);
	break;
}
case "invalid" :{
	assertTrue(login.loginError().isDisplayed(), "Error Message should be displayed for invalid login "+ Tc);
	break;
}
case "PassRequired":{
	assertTrue(login.loginPassRequired().isDisplayed(), "Password field should be empty "+ Tc);
	break;
}
case "UserRequired":{
	System.out.println(login.loginUserRequired().isDisplayed() +login.loginUserRequired().getText());
	assertTrue(login.loginUserRequired().isDisplayed(), "UserName field should be empty "+ Tc);
	
	break;
}
case "UserRequiredPassRequired":{
	assertTrue(login.loginPassRequired().isDisplayed()&&login.loginPassRequired().isDisplayed(), "UserName & Password field should be empty "+ Tc);
	break;
}
default:
	Assert.fail("Unexpected expected result from Excel: " + expectedResult);
	
}
	
	
}
	
}
