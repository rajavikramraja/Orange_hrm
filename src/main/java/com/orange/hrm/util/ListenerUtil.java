package com.orange.hrm.util;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenerUtil implements ITestListener {
	ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        ExtentManager.getInstance().flush();
        
        
        
        
    }
}