package com.orange.hrm.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicityWait {
	protected static WebDriverWait wait;
	static {
		wait=new WebDriverWait(BaseTest.getdriver(), Duration.ofSeconds(30));
		// TODO Auto-generated constructor stub
	}
public static WebElement visibleByLocator(By Locators) {
	return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators));
}
public static List<WebElement> visibleAllByLocator(By Locators) {
 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators));
}
public static WebElement presenceByLocator(By Locators) {
	return wait.until(ExpectedConditions.presenceOfElementLocated(Locators));
}
public static List<WebElement> presenceAllByLocator(By Locators) {
	return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locators));
}
public static WebElement clickByLocator(By Locators) {
	return wait.until(ExpectedConditions.elementToBeClickable(Locators));
}

}
