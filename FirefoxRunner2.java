package android2.VideoEngager;

import java.awt.AWTException;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirefoxRunner2 {

	WebDriver firefox;

	@BeforeTest
	public void setUp() throws AWTException {
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile("default");
		// this make firefoxProfile to block web page images
		// firefoxProfile.setPreference("permissions.default.image", 2);
		firefox = new FirefoxDriver(firefoxProfile);
		firefox.manage().window().maximize();
		new Minimize().minimize();
		firefox.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		firefox.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

	}

	@Test
	public void test1() throws InterruptedException {

		System.out.println("test started");
		firefox.get("https://videome.leadsecure.com/testtes");
		
		startVideoCall();
		
		
		
//		Thread.sleep(7000);
//		System.out.println("1");
//		firefox.get("http://blank.org/");
//		firefox.get("https://videome.leadsecure.com/testtes");
//
//		Thread.sleep(2000);
//		System.out.println("2");
//		firefox.get("http://blank.org/");
//		firefox.get("https://videome.leadsecure.com/testtes");
//		Thread.sleep(2000);
//
//		System.out.println("3");
//		firefox.get("http://blank.org/");
//		firefox.get("https://videome.leadsecure.com/testtes");
		Thread.sleep(120000);

	}
	public void startVideoCall() {
		System.out.println("Firefox: try to startVideoCall");
		WebElement callButton;
		WebDriverWait wait = new WebDriverWait(firefox, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'callButton_1')]")));
		callButton = firefox.findElement(By.xpath("//*[contains(@id, 'callButton_1')]"));
		if (callButton.isDisplayed()) {
			callButton.click();
		}
		callButton = firefox.findElement(By.xpath("//*[contains(@id, 'callButton_3')]"));
		if (callButton.isDisplayed()) {
			callButton.click();
		}
		System.out.println("Firefox clicked on StartVideoCall button.");
	}
	
	
}
