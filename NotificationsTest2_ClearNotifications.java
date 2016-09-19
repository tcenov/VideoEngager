package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import java.io.IOException;
import java.net.MalformedURLException;
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

public class NotificationsTest2_ClearNotifications {

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
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {

		System.out.println("test started");

		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
		print("Android pressed Home button - app works in background");
		pause(5);
		openNotifications();
		
		pause(5);
		openNotifications();
		clearNotifications();
		pause(10);
	}
	void clearNotifications() {
		// Required Login
		// openNotifications();
		Boolean isPresent = driver.findElements(By.className("android.widget.Button")).size() > 0;
		if (isPresent) {
			List<WebElement> allButtons = driver.findElements(By.className("android.widget.Button"));
			try {
				allButtons = driver.findElements(By.className("android.widget.Button"));
				print("allButtons.size() = " + allButtons.size());
				for (int i = 0; i < allButtons.size(); i++) {
					String buttonName = allButtons.get(i).getText();
					print("Android: Found button " + i + " with name= " + buttonName);
					if (Objects.equals(buttonName, new String(""))) {
						print("Android: Found clear notification button");
						allButtons.get(i).click();
						print("Android: Clear notifications.");
						return;
					} else {
						print("Android: This button is not clear notification button");
					}
				}
			} catch (Exception e) {
				print("Android: There are no any notifications");
				pressBackButton();
			}
		} else {
			print("Android: There are no any notifications");
			pressBackButton();
		}
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

	void pressBackButton() {
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		print("Android pressed Back button");
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