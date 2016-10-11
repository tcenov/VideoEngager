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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SuppressWarnings("rawtypes")

public class AndroidMessageVerificationsTest {

	AppiumDriver driver;

	@BeforeTest
	public void setUp() throws IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "5.5.1");
		capabilities.setCapability("platformName", "Android");
		// capabilities.setCapability("appPackage", "com.android.calculator2");
		// capabilities.setCapability("appActivity",
		// "com.android.calculator2.Calculator");
		capabilities.setCapability("appPackage", "com.leadsecure.agent");
		capabilities.setCapability("appActivity", "com.leadsecure.core.ui.LoginActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws InterruptedException {

		System.out.println("test started");

		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
		// print("Android pressed Home button - app works in background");
		pause(5);

		// getAppBackInForeground();

		// openNotifications();
		// pause(5);
		// openNotifications();
		pause(10);
		startConversation();
		
		pause(20);
		
		verifyMessage("alabala");
		pause(22);
	}

	void verifyMessage(String message) {
		List<WebElement> messages = driver.findElements(By.id("chatRowInMessageText"));
		String messageText = null;
		for (WebElement webElement : messages) {
			messageText = webElement.getText();
			if (Objects.equals(messageText, message)) {
				print("Android verified message: " + message);
				return;
			}
		}
		Assert.assertEquals(messageText, message);
	}

	void print(String text) {
		System.out.println(text);
	}

	void startConversation() {
		print("Android: Waiting up to 20 sec. for visitors");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingCallerName")));
		List<WebElement> visitors = driver.findElements(By.id("ongoingCallerName"));
		for (WebElement webElement : visitors) {
			print("Android: You have visitor with name: " + webElement.getText());
		}
		visitors.get(0).click();
		try {
			String visitorName = driver.findElement(By.id("txtTitle")).getText();
			print("Started Conversation with " + visitorName);
		} catch (Exception e) {
			print("Android: Error on get visitor's name ");
		}

	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	void pressBackButton() {
		((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		print("Android pressed Back button");
	}

	void getAppBackInForeground() {
		print("Android: App is in foreground now");
		driver.runAppInBackground(1);
	}

	void openNotifications() {
		((AndroidDriver) driver).openNotifications();
		print("Android opened Notifications.");
	}

	void adbExecuteComand(String command) throws IOException {
		Runtime.getRuntime().exec(command);
	}

	void acceptRejectNotification(String acceptOrReject) {
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

	void hideKeyboard() {
		System.out.println("Android: Hide keyboard.");
	}

	@AfterTest
	public void End() throws IOException {
		if (driver != null) {
			driver.quit();
		}
	}
}