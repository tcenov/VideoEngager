package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@SuppressWarnings("rawtypes")

public class InstallFromCrashlitics {

	AppiumDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "5.1.1");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "io.crash.air-2");
		capabilities.setCapability("appActivity", "io.crash.air.ui.MainActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void test() throws IOException, InterruptedException {

		System.out.println("test started");
		Thread.sleep(5000);

	}

	@AfterTest
	public void End() throws IOException {
		if (driver != null) {
			driver.quit();
		}
	}
}