package com.orange.hrm.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orange.hrm.base.BaseTest;

public class ListenerUtil extends BaseTest implements ITestListener, IAnnotationTransformer{
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
        Object[] params = result.getParameters();
        String dataPart = "NoData";

        if (params != null && params.length > 0) {
         dataPart = params[0].toString().replaceAll("[^a-zA-Z0-9]", "_");}
        takeScreenShot(result.getMethod().getMethodName()+"-"+dataPart);
        
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        ExtentManager.getInstance().flush(); 
        
    }
@Override
public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	// TODO Auto-generated method stub
	if(annotation.getRetryAnalyzerClass()==null) {
		annotation.setRetryAnalyzer(RetryClass.class);
	}
}
}