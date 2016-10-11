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

public class AndroidCleanRun {

	AppiumDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("appPackage", "com.android.calculator2");
//		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		capabilities.setCapability("appPackage", "com.leadsecure.agent");
		capabilities.setCapability("appActivity", "com.leadsecure.core.ui.LoginActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws IOException, InterruptedException {
		pause(10);
		System.out.println("test started");
		adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
		
		pause(180);
		//lockScreen();
		 
	}

	@Test
	public void test180() throws IOException, InterruptedException {
		pause(10);
		System.out.println("Only this test started 180");
		
		pause(180);
		//lockScreen();
		 
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