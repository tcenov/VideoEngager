package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AndroidCleanRun {

	AppiumDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		// capabilities.setCapability("appPackage", "com.leadsecure.agent");
		// capabilities.setCapability("appActivity",
		// "com.leadsecure.core.ui.LoginActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@Test
	public void testaaa() throws IOException, InterruptedException {
		pause(1);
		System.out.println("test started");

		// print("Android pressed Home button - app works in background");

		// ((AppiumDriver) driver).closeApp();
	
		
		adbExecuteComand("adb shell input keyevent 187");
		By closeAnyAppButton = By.xpath("//android.widget.ImageView[contains(@resource-id,'dismiss_task')]");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(closeAnyAppButton));
		int i = driver.findElements(closeAnyAppButton).size();
		while (i > 0) {
			print("view " + i + " running apps.");
			int j = 1;
			List<WebElement> apps = driver.findElements(closeAnyAppButton);
			for (WebElement app : apps) {
				try {
					clickOnSelectorIfIsPresent(closeAnyAppButton);
				} catch (Exception e) {
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
					adbExecuteComand("adb shell input keyevent 187");
					clickOnSelectorIfIsPresent(closeAnyAppButton);
				}
				print("closed " + j + " apps");
				j++;
			}
			i = driver.findElements(closeAnyAppButton).size();
		}

		pause(3);
		// lockScreen();
	}

	private void clickOnSelectorIfIsPresent(By selector) {
		Boolean isPresent = driver.findElements(selector).size() > 0;
		if (isPresent) {
			driver.findElement(selector).click();
		} else {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
			driver.findElement(selector).click();
		}
	}

	void print(String text) {
		System.out.println(text);
	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	void startApp() {
		driver.launchApp();
		print("Android launched application.");
	}

	void adbExecuteComand(String command) throws IOException {
		Runtime.getRuntime().exec(command);
	}

	@AfterTest
	public void End() throws IOException {
		if (driver != null) {
			driver.quit();
		}
	}
}