package android2.VideoEngager;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class Chrome {
	WebDriver chrome;

	@BeforeTest
	public void setUp() throws AWTException {
		print("Chrome: chrome.setUp() is starting");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		chrome = new ChromeDriver();
		chrome.manage().window().maximize();
		new Minimize().minimize();
		chrome.get("https://videome.leadsecure.com");
		chrome.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		chrome.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
	}
	
	void login(String email, String password) {
		print("Proceed with Agent from chrome login.");

		typeTextInSelector(By.name("Email"), email);
		typeTextInSelector(By.name("Password"), password);
		clickOnNameIfIsPresent("ENTER");
		
		WebDriverWait wait = new WebDriverWait(chrome, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("testa")));
		print("Chrome: You are logged in as agent from Chrome");
	}
	
	
	private void clickOnIdIfIsPresent(String id) {
		Boolean isPresent = chrome.findElements(By.id(id)).size() > 0;
		if (isPresent) {
			chrome.findElement(By.id(id)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(chrome, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			chrome.findElement(By.id(id)).click();
		}
	}	
		
	private void clickOnNameIfIsPresent(String name) {
		Boolean isPresent = chrome.findElements(By.name(name)).size() > 0;
		if (isPresent) {
			chrome.findElement(By.name(name)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(chrome, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			chrome.findElement(By.name(name)).click();
		}
	}

	public boolean isElementPresent(By selector) {
		boolean found = true;
		try {
			WebDriverWait wait = new WebDriverWait(chrome, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		} catch (NoSuchElementException e) {
			print("Chrome: doesn't found selector.");
			found = false;
		}
		return found;
	}
	
	public void typeTextInSelector(By selector, String text) {
		try {
			chrome.findElement(selector).sendKeys(text);
		} catch (NoSuchElementException e) {
		}
	}
	
	public String generateName() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm_ss");
		String name = dateFormat.format(new Date())+ "_name";
		return name;
	}
	
	public String generateEmail() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm_ss");
		String email = dateFormat.format(new Date())+ "@mail.com";
		return email;
	}
	
	public void resizeWindow(int width, int height) {
		chrome.manage().window().setSize(new Dimension(width, height));
	}
	
	void pause(int seconds) throws InterruptedException {
		System.out.println("Chrome: Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(chrome, 30);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) chrome).executeScript("return document.readyState").equals("complete");
			}
		});
		print("Chrome: Page is loaded.");
	}

	void print(String text) {
		System.out.println(text);
	}
}