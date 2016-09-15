package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@SuppressWarnings("rawtypes")

public class VideoEngager {

	AppiumDriver android;
	Dimension size;

	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "009511b51b8a18eb");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "5.5.1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.leadsecure.agent");
		capabilities.setCapability("appActivity", "com.leadsecure.core.ui.LoginActivity");
		android = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		android.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {
		size = android.manage().window().getSize();
		print("Device screen size is " + size);

		login("tester2006@abv.bg", "Tarator1");
		
		// makeVideoCallFromVisitorList();
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingEmailText")));
		WebElement visitor = android.findElement(By.id("ongoingEmailText"));
		print("Your first visitor is: " + visitor.getText());

		WebElement call = android.findElement(By.id("txtCall"));

		// Get visitor location to swipe to Call button
		Point point = call.getLocation();
		int xcoord = point.getX() - 96;
		int ycoord = point.getY();
		print("call button coordinates are " + xcoord + "   " + ycoord);
		TouchAction action = new TouchAction((MobileDriver) android);
		action.longPress(call).moveTo(xcoord, ycoord).perform().release();

		//logout();

		print("krai na testa");
		Thread.sleep(8000);

	}

	void makeVideoCallFromVisitorList() {
		WebDriverWait wait = new WebDriverWait(android, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingCallContainerRecyclerView")));

		WebElement visitorsList = android.findElement(By.id("ongoingCallContainerRecyclerView"));
		List<WebElement> visitors = visitorsList.findElements(By.id("rowFront"));
		print("You have only " + visitors.size() + " visitors");

		// Get visitor location to swipe to Call button
		Point point = visitors.get(0).getLocation();
		int xcord = point.getX();
		int ycord = point.getY();

		TouchAction action = new TouchAction((MobileDriver) android);
		action.longPress(visitors.get(0)).moveTo(xcord - 50, ycord).perform();
	}

	void login(String email, String password) {
		print("Proceed with login.");
		clickOnNameIfIsPresent("Login");
		android.findElement(By.name("Email")).sendKeys(email);
		android.findElement(By.id("signInPasswordEdit")).sendKeys(password);
		clickOnNameIfIsPresent("ENTER");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("vanityTitleText")));
		print("You are logged in");
	}
	
	void logout() throws InterruptedException {
		print("Proceed with logout.");
		// Navigate to logout and logout
		clickOnIdIfIsPresent("vanityMenuButton");
		clickOnNameIfIsPresent("Logout");
		clickOnNameIfIsPresent("Yes");
	}

	void clickOnIdIfIsPresent(String id) {
		Boolean isPresent = android.findElements(By.id("id")).size() > 0;
		if (isPresent) {
			android.findElement(By.id("id")).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
			android.findElement(By.id("id")).click();
		}
	}

	void clickOnNameIfIsPresent(String name) {
		Boolean isPresent = android.findElements(By.name("name")).size() > 0;
		if (isPresent) {
			android.findElement(By.name("name")).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
			android.findElement(By.name("name")).click();
		}
	}

	void print(String text) {
		System.out.println(text);
	}

	@AfterTest
	public void End() throws IOException {
		if (android != null) {
			android.closeApp();
			android.quit();
		}
	}
}