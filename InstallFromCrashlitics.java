package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SuppressWarnings("rawtypes")

public class InstallFromCrashlitics {

	AppiumDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "5.1.1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "io.crash.air");
		capabilities.setCapability("appActivity", "io.crash.air.ui.MainActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void install() throws IOException, InterruptedException {
		System.out.println("Uninstall application");
		adbExecuteComand("adb shell am force-stop com.leadsecure.agent");
		adbExecuteComand("adb shell pm clear com.leadsecure.agent");
		adbExecuteComand("adb uninstall com.leadsecure.agent");
		Thread.sleep(2000);
		System.out.println("Uninstall finished");
		System.out.println("Install started");
		List<WebElement> elements = driver.findElements(By.id("app_package"));
		System.out.println("elements.size() = " + elements.size());
		for (WebElement webElement : elements) {
			webElement.getText();
			System.out.println(webElement.getText());
			if (Objects.equals(webElement.getText(), new String("com.leadsecure.agent"))) {
				webElement.click();
				System.out.println("com.leadsecure.agent package clicked");
				break;
			}
		}
		
		try {
			clickOnIdIfIsPresent("app_action_button");
			System.out.println("Download agent should be started");
		} catch (Exception e) {
			System.out.println("Error when try to click on download button");
		}

		Thread.sleep(160000);
	}

	void adbExecuteComand(String command) throws IOException {
		Runtime.getRuntime().exec(command);
		
	}
	
	private void clickOnIdIfIsPresent(String id) {
		Boolean isPresent = driver.findElements(By.id(id)).size() > 0;
		if (isPresent) {
			driver.findElement(By.id(id)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			driver.findElement(By.id(id)).click();
		}
	}
}