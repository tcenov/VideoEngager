package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@SuppressWarnings("rawtypes")

public class Android {

	AppiumDriver android;
	Firefox firefox;

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "009511b51b8a18eb");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.leadsecure.agent");
		capabilities.setCapability("appActivity", "com.leadsecure.core.ui.LoginActivity");
		cleanUpAndroid();
		android = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		android.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firefox = new Firefox();
	}

	void startConversation() {
		print("Waiting up to 20 sec. for visitors");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingCallerName")));
		List<WebElement> visitors = android.findElements(By.id("ongoingCallerName"));
		for (WebElement webElement : visitors) {
			print("you have visitor with name: " + webElement.getText());
		}
		visitors.get(0).click();
		String visitorName = null;
		try {
			visitorName = visitors.get(0).getText();
		} catch (Exception e) {
			print(" Error on get name from visitor");
		}
		print("Started Conversation with " + visitorName);
	}

	void closeConversation() {
		clickOnIdIfIsPresent("btnLeft");
	}

	void sendMessage(String message) {
		WebElement textField = android.findElement(By.id("chatBottomLayout"));
		textField.click();
		print("clicked in message field.");
		textField.clear();
		print("cleared message field.");
		textField.sendKeys(message);
		clickOnNameIfIsPresent("Send");
		print("Message sent from android.");
	}

	void startVideoCall() {
		clickOnIdIfIsPresent("btnRight");
		print("Clicked on StartVideoCall button.");
	}

	void stopVideoCall() {
		clickOnIdIfIsPresent("incallRejectButton");
		print("Clicked on StopVideoCall button.");
	}

	void inVideoCallCameraButtonClick() {
		clickOnIdIfIsPresent("incallCameraButton");
		print("Clicked on incallCameraButton button.");
	}

	public void videoCallGetTextFromElements() {
		WebDriverWait wait = new WebDriverWait(android, 25);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
		List<WebElement> list = android.findElements(By.className("android.widget.TextView"));
		int i = 0;
		for (WebElement webElement : list) {
			try {
				print("text from element " + i + "= " + webElement.getText());
				i++;
			} catch (Exception e) {
				print("Greshka pri webElement.getText()");
			}
		}
	}

	void login(String email, String password) {
		print("Proceed with android login.");
		clickOnIdIfIsPresent("loginArrow");
		android.findElement(By.name("Email")).sendKeys(email);
		android.findElement(By.id("signInPasswordEdit")).sendKeys(password);
		clickOnNameIfIsPresent("ENTER");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("vanityTitleText")));
		print("You are logged in from android device");
	}

	void logout() throws InterruptedException {
		print("Proceed with logout.");
		// Navigate to logout and logout
		clickOnIdIfIsPresent("vanityMenuButton");
		clickOnNameIfIsPresent("Logout");
		clickOnNameIfIsPresent("Yes");
	}

	private WebElement getWebElement(By selector) {
		try {
			WebElement element = android.findElement(selector);
			return element;
		} catch (Exception e) {
			WebElement element = android.findElement(selector);
			return element;
		}
	}

	public boolean isElementPresent(By selector) {
		boolean found = true;
		try {
			WebDriverWait wait = new WebDriverWait(android, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		} catch (NoSuchElementException e) {
			print("doesn't found selector");
			found = false;
		}
		return found;
	}

	public void typeTextInSelector(By selector, String text) {
		try {
			android.findElement(selector).click();
			android.findElement(selector).sendKeys(text);
		} catch (NoSuchElementException e) {
		}
	}

	public void clickOnSelector(By selector) throws InterruptedException {
		WebElement element = getWebElement(selector);
		try {
			element.click();
		} catch (WebDriverException toci) {
			pause(5);
			getWebElement(selector);
		}
	}

	private void clickOnIdIfIsPresent(String id) {
		Boolean isPresent = android.findElements(By.id(id)).size() > 0;
		if (isPresent) {
			android.findElement(By.id(id)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			android.findElement(By.id(id)).click();
		}
	}

	private void clickOnNameIfIsPresent(String name) {
		Boolean isPresent = android.findElements(By.name(name)).size() > 0;
		if (isPresent) {
			android.findElement(By.name(name)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			android.findElement(By.name(name)).click();
		}
	}

	void lockScreen() {
		try {
			Runtime.getRuntime().exec("adb shell am force-stop io.appium.unlock");
			Runtime.getRuntime().exec("adb shell input keyevent 26");
			print("Screen is locked");
		} catch (IOException e) {
			print("Something wrong in lock screen!!!");
			e.printStackTrace();
		}
	}

	void unlockScreen() {
		try {
			Runtime.getRuntime().exec("adb shell input keyevent 82");
			print("Screen is unlocked.");
		} catch (IOException e) {
			print("Something wrong in lock screen!!!");
			e.printStackTrace();
		}
	}

	void unlockScreenWithAppium() {
		try {
			Runtime.getRuntime().exec("adb shell am start -n io.appium.unlock/.Unlock");
			print("Screen is unlocked by Appium");
		} catch (IOException e) {
			print("Something wrong in lock screen!!!");
			e.printStackTrace();
		}
	}

	void print(String text) {
		System.out.println(text);
	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	@AfterClass
	void cleanUpAndroid() throws IOException {
		print("Clean up android");
		Runtime.getRuntime().exec("adb shell input keyevent 26");
		Runtime.getRuntime().exec("adb shell am force-stop io.appium.unlock");
		Runtime.getRuntime().exec("adb shell am force-stop com.leadsecure.agent");
		Runtime.getRuntime().exec("adb shell pm clear com.leadsecure.agent");
	}
}