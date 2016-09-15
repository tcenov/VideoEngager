package android2.VideoEngager;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Firefox {

	WebDriver firefox;

	@BeforeTest
	public void setUp() throws AWTException {
		print("firefox.setUp() is starting");
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
		FirefoxProfile profile = new ProfilesIni().getProfile("default");
		desiredCapabilities.setCapability("firefox_profile", profile);
		firefox = new FirefoxDriver(desiredCapabilities);
		// ProfilesIni profile = new ProfilesIni();
		// FirefoxProfile firefoxProfile =
		// profile.getProfile("selenium");//using firefox default profile
		// //firefoxProfile.setPreference("permissions.default.image", 2); //
		// this make firefoxProfile to block web page images
		// firefox = new FirefoxDriver(firefoxProfile);
		firefox.manage().window().maximize();
		new Minimize().minimize();
		firefox.get("https://videome.leadsecure.com/testtes");
		firefox.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firefox.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		firefox.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
	}

	public void login() {
		String title = firefox.getTitle();
		if (Objects.equals(title, new String("Live Video Session"))) {
			print(title + " is loaded on Firefox");
		} else {
			print("There is something wrong !!!");
		}
		if (isElementPresent(By.xpath("//*[contains(@id, 'peer_email_video')]"))) {
			print("You are logged in from Firefox");
		}
	}

	void logout() {
		print("You are logged out");
	}

	public void answerVideoCall() {
		// answer_call_button selector
		// *[@id='answer_call_buttonF37k1rLhVbmpxwf9']
		if (isElementPresent(By.xpath("//*[contains(@id, 'answer_call_button')]"))) {
			firefox.findElement(By.xpath("//*[contains(@id, 'answer_call_button')]")).click();
			print("Answer video call from Firefox");
		} else {
			print("Greshka pri video call answer!!!");
		}
	}

	public void SendMessage(String message) {
		// id of "Message field" webelement = instacollab_chat_message1F37k1rLhVbmpxwf9
		if (isElementPresent(By.xpath("//*[contains(@id, 'instacollab_chat_message')]"))) {
			WebElement messageField = firefox.findElement(By.xpath("//*[contains(@id, 'instacollab_chat_message')]"));
			messageField.click();
			messageField.sendKeys(message);
			print("enter message in field");
		} else {
			print("Greshka pri enter message in field!!!");
		}
		//id of "Enter" webelement = instacollab_chat_button1Fv1231231v134
		if (isElementPresent(By.xpath("//*[contains(@id, 'instacollab_chat_button1')]"))) {
			WebElement enter = firefox.findElement(By.xpath("//*[contains(@id, 'instacollab_chat_button1')]"));
			enter.click();
			print("Message sent from Firefox");
		} else {
			print("Greshka pri send message from Firefox!!!");
		}
	}
	
	public void CameraButtonClick(){
		clickOnIdIfIsPresent("showHideVideo");
	}
	
	public void MicrophoneButtonClick(){
		clickOnIdIfIsPresent("showHideAudio");
	}
	
	public void CallButtonClick(){
		//id="callButton_3F37k1rLhVbmpxwf9"
		if (isElementPresent(By.xpath("//*[contains(@id, 'callButton')]"))) {
			WebElement call = firefox.findElement(By.xpath("//*[contains(@id, 'callButton')]"));
			call.click();
			print("Called from Firefox");
		} else {
			print("Greshka pri call from Firefox!!!");
		}
	}
	
	public void connectButtonClick() throws InterruptedException{
		clickOnSelector(By.xpath("//*[contains(@id, 'cancel_call_button')]"));
	}
	
	
	private WebElement getWebElement(By selector) {
		try {
			WebElement element = firefox.findElement(selector);
			return element;
		} catch (Exception e) {
			WebElement element = firefox.findElement(selector);
			return element;
		}
	}
	
	
	public void clickOnSelector(By selector) throws InterruptedException {
		WebElement element = getWebElement(selector);
		try {
			element.click();
		} catch (WebDriverException toci) {
			pause(5);
			getWebElement(selector);
		}
	}
	
	public boolean isElementPresent(By selector) {
		boolean found = true;
		try {
			WebDriverWait wait = new WebDriverWait(firefox, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		} catch (NoSuchElementException e) {
			print("doesn't found selector");
			found = false;
		}
		return found;
	}
	
	public void typeTextInSelector(By selector, String text) {
		try {
			firefox.findElement(selector).sendKeys(text);
		} catch (NoSuchElementException e) {
		}
	}
	
	private void clickOnIdIfIsPresent(String id) {
		Boolean isPresent = firefox.findElements(By.id(id)).size() > 0;
		if (isPresent) {
			firefox.findElement(By.id(id)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(firefox, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			firefox.findElement(By.id(id)).click();
		}
	}

	private void clickOnNameIfIsPresent(String name) {
		Boolean isPresent = firefox.findElements(By.name(name)).size() > 0;
		if (isPresent) {
			firefox.findElement(By.name(name)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(firefox, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			firefox.findElement(By.name(name)).click();
		}
	}

	void shareCameraMicroPhone() throws AWTException, InterruptedException {
		// firefox.manage().window().setSize(new Dimension(500, 500));
		// firefox.manage().window().maximize();
		pause(2);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		for (int i = 0; i < 6; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			// pause(1);
			Thread.sleep(500);
		}
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
		print("clicked on shared devices");
		pause(1);
		for (int i = 0; i < 5; i++) {
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(500);
		}
		pause(1);
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
		// click mouse left button
		// robot.mousePress(KeyEvent.BUTTON1_MASK );
		// robot.mouseRelease(KeyEvent.BUTTON1_MASK );
	}
	
	void print(String text) {
		System.out.println(text);
	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	public void resizeWindow(int width, int height){
		firefox.manage().window().setSize(new Dimension(width, height));
	}
	
	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(firefox, 30);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) firefox).executeScript("return document.readyState").equals("complete");
			}
		});
		print("Page is loaded.");
	}

	@AfterTest
	void close() {
		if (firefox != null) {
			firefox.quit();
			print("Firefox closed");
		}
	}
}
