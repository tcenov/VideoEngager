package android2.VideoEngager;

import java.awt.AWTException;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirefoxMessageVerification {

	WebDriver firefox;

	@BeforeTest
	public void setUp() throws AWTException {
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile("default");
		// this make firefoxProfile to block web page images
		firefoxProfile.setPreference("permissions.default.image", 2);
		firefox = new FirefoxDriver(firefoxProfile);
		firefox.manage().window().maximize();
		new Minimize().minimize();
		firefox.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firefox.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		firefox.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

		// firefox.get("http://dir.bg");
		firefox.get("https://videome.leadsecure.com/testtes");
	}

	@Test
	public void test1() throws InterruptedException {

		pause(50);
		verifyMessage("alabala");

	}

	// .wd-bubble>p
	void verifyMessage(String message) {
		List<WebElement> messages = firefox.findElements(By.cssSelector(".wd-bubble>p"));
		print("messages.size() = " + messages.size());
		String messageText = null;
		for (WebElement webElement : messages) {
			messageText = webElement.getText();
			print(messageText);
			 if (Objects.equals(messageText, message)) {
			 print("Firefox verified message: " + message);
			 return;
			 }
		}
		 Assert.assertEquals(messageText, message);
	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Firefox: Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	void print(String text) {
		System.out.println(text);
	}
}
