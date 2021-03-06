package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SuppressWarnings("rawtypes")

public class Test_AndroidStartApp {

	AppiumDriver driver;

	@BeforeTest
	public void setUp() throws IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "test");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "5.5.1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException, IOException {

		System.out.println("test started");

		pause(10);
		// Runtime.getRuntime().exec("adb shell input keyevent 26");
		adbExecuteComand("adb shell am force-stop com.android.calculator2");
		pause(9);
 
		startApp();
		pause(7);

	}

	// JavascriptExecutor javascript = (JavascriptExecutor) driver;
	// javascript.executeScript("mobile: scrollTo", elementName);

	void clickNotificationButton(String acceptOrReject) {
		acceptOrReject = acceptOrReject.toLowerCase();
		List<WebElement> buttons = driver.findElements(By.xpath("*//android.widget.Button"));
		for (int j = 0; j < buttons.size(); j++) {
			String buttonName = buttons.get(j).getText().toLowerCase();
			if (Objects.equals(buttonName, acceptOrReject)) {
				buttons.get(j).click();
				return;
			}
		}
	}

	void startApp() {
		try {
			driver.launchApp();
		} catch (Exception e) {
			// Ignore "... but it's already started" error.
		}
		print("Android launched application.");
	}

	void getAllNotifications() {
		List<WebElement> elements = driver.findElements(By.xpath("//android.widget.TextView"));
		for (WebElement webElement : elements) {
			print(webElement.getText());
		}
		print("elements.size()" + Integer.toString(elements.size()));
		elements = driver.findElements(By.id("title"));
		for (WebElement webElement : elements) {
			print(webElement.getText());
		}
		print("elements.size()" + Integer.toString(elements.size()));
		String title;
		int i;
		for (i = 0; i < elements.size(); i++) {
			title = elements.get(i).getText();
			if (Objects.equals(title, new String("Video Agent"))) {
				print("Found video agent notification.");
				return;
			} else {
				print("Tthere are no agent notifications");
			}
		}
	}

	void print(String text) {
		System.out.println(text);
	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	void openNotifications() {
		((AndroidDriver) driver).openNotifications();
		print("Android opened Notifications.");
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