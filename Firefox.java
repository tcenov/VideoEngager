package android2.VideoEngager;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Firefox {

	WebDriver firefox;
	String agenUrl = "";
	String geckodriverPath = "D:\\geckodriver-v0.10.0-win64\\geckodriver.exe";
	
	By inRequestedFormNameField_ = By.xpath("//*[contains(@id, 'instacollab_name')]");
	By inRequestedFormEmailField_ = By.xpath("//*[contains(@id, 'instacollab_email')]");
	By inRequestedFormPhoneField_ = By.xpath("//*[contains(@id, 'instacollab_phone')]");
	By inRequestedFormContinueButton_ = By.xpath("//*[contains(@id, 'continue-button')]");
	By connectButton_ = By.xpath("//*[contains(@id, 'cancel_call_button')]");
	By callButton_1_ = By.xpath("//*[contains(@id, 'callButton_1')]");
	By callButton_3_ = By.xpath("//*[contains(@id, 'callButton_3')]");
	By chatMessages_= By.cssSelector(".wd-bubble>p");
	By answerCallButton_ = By.xpath("//*[contains(@id, 'answer_call_button')]");
	By chatMessageField_ = By.xpath("//*[contains(@id, 'instacollab_chat_message')]"); 
	By sendMessageButton_ = By.xpath("//*[contains(@id, 'instacollab_chat_button1')]"); 
	By localVideo_ = By.xpath("//*[contains(@id, 'localVideo')]");
			
	@BeforeTest
	public void setUp() throws AWTException {
		print("Firefox: firefox.setUp() is starting");
		System.setProperty("webdriver.gecko.driver", geckodriverPath);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities("firefox", geckodriverPath, Platform.ANY);
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
		// firefox.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firefox.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		firefox.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
	}

	public void join() {
		String title = firefox.getTitle();
		if (Objects.equals(title, new String("Live Video Session"))) {
			print("Firefox: " + title + " is loaded on Firefox");
		} else {
			print("Firefox: There is something wrong !!!");
		}
		if (isElementPresent(By.xpath("//*[contains(@id, 'peer_email_video')]"))) {
			print("Firefox: You are joined as prospector in from Firefox");
		}
	}

	void logout() {
		print("Firefox: You are logged out from Firefox.");
	}

	public void answerVideoCall() {
		print("Firefox: try to answerVideoCall");
		if (isElementPresent(answerCallButton_)) {
			firefox.findElement(answerCallButton_).click();
			print("Firefox answered video call.");
		} else {
			print("Firefox: Greshka pri video call answer!!!");
		}
	}
	
	public void verifyVideoFromAgent() throws IOException {
		print("Firefox: try to verifyVideoFromAgent");
		//id=remoteVideoF37k1rLhVbmpxwf9
		if (isElementPresent(By.xpath("//*[contains(@id, 'remoteVideo')]"))) {
			print("Firefox: verified video received from agent.");
		} 
	}
	
	public void verifyOwnVideo() throws IOException {
		print("Firefox: try to verifyOwnVideo");
		//id=localVideoF37k1rLhVbmpxwf9
		if (isElementPresent(By.xpath("//*[contains(@id, 'localVideo')]"))) {
			print("Firefox: verified video send from prospector.");
		}
	}
	
	public void verifyStoppedOwnVideo() throws IOException {
		print("Firefox: try to verifyStoppedOwnVideo");
		WebElement localVideo = firefox.findElement(localVideo_);
		Boolean isDisplayed = localVideo.isDisplayed();
		if (isDisplayed = false) {
			print("Firefox: verified stopped own video");
		} else {
			 throw new IOException("Firefox: video is not stopped properly");
		}
	}
	
	public void SendMessage(String message) {
		print("Firefox: try to SendMessage");
		if (isElementPresent(chatMessageField_)) {
			WebElement messageField = firefox.findElement(chatMessageField_);
			messageField.click();
			messageField.sendKeys(message);
			print("Firefox enter message in field");
		} else {
			print("Firefox: Greshka pri enter message in field!!!");
		}
		if (isElementPresent(sendMessageButton_)) {
			WebElement enter = firefox.findElement(sendMessageButton_);
			enter.click();
			print("Firefox: Message sent.");
		} else {
			print("Firefox: Greshka pri send message from Firefox!!!");
		}
	}

	void verifyMessage(String message) throws InterruptedException {
		print("Firefox: try to verifyMessage");
		pause(2);
		List<WebElement> messages = firefox.findElements(chatMessages_);
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

	public void cameraButtonClick() {
		print("Firefox: try to cameraButtonClick");
		clickOnIdIfIsPresent("showHideVideo");
		print("Firefox: showHideVideo.");
	}

	public void muteMicrophone() {
		print("Firefox: try to muteMicrophone");
		clickOnIdIfIsPresent("showHideAudio");
		print("Firefox: muted microphone.");
	}
	
	public void unmuteMicrophone() {
		print("Firefox: try to unmuteMicrophone");
		clickOnIdIfIsPresent("showHideAudio");
		print("Firefox: unmuted microphone.");
	}
	
	public void stopVideoCall() {
		print("Firefox: try to stopVideoCall");
		clickOnIdIfIsPresent("hangupButton");
		print("Firefox stopped video call.");
	}

	public void startVideoCall() {
		print("Firefox: try to startVideoCall");
		WebElement callButton;
		WebDriverWait wait = new WebDriverWait(firefox, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(callButton_1_));
		callButton = firefox.findElement(callButton_1_);
		if (callButton.isDisplayed()) {
			callButton.click();
		}
		callButton = firefox.findElement(callButton_3_);
		if (callButton.isDisplayed()) {
			callButton.click();
		}
		print("Firefox clicked on StartVideoCall button.");
	}
	
	public void callButtonFromHomeClick() {
		print("Firefox: try to callButtonFromHomeClick");
		WebElement call;
		WebDriverWait wait;
		try {
			call = firefox.findElement(callButton_3_);
			wait = new WebDriverWait(firefox, 15);
			wait.until(ExpectedConditions.elementToBeClickable(call));
			call.click();
			print("Firefox called.");
		} catch (Exception e) {
			print("Firefox: Greshka pri call");
		}
	}

	public void callButtonFromConversationClick() {
		print("Firefox: try to callButtonFromConversationClick");
		// callButtonF37k1rLhVbmpxwf9
		WebElement call;
		WebDriverWait wait;
		try {
			call = firefox.findElement(callButton_1_);
			wait = new WebDriverWait(firefox, 15);
			wait.until(ExpectedConditions.elementToBeClickable(call));
			call.click();
			print("Firefox called.");
		} catch (Exception e) {
			print("Greshka pri call");
		}
	}

	public void connectButtonClick() throws InterruptedException {
		print("Firefox: try to connectButtonClick");
		clickOnSelector(connectButton_);
		print("Firefox: clicked on Connect button");
	}

	public void fillRequestedForm(String name,String email, String phone) throws InterruptedException {
		print("Firefox: try to fillRequestedForm");
		typeTextInSelector(inRequestedFormNameField_, name);
		typeTextInSelector(inRequestedFormEmailField_, email);
		typeTextInSelector(inRequestedFormPhoneField_, phone);
		clickOnSelector(inRequestedFormContinueButton_);	
		print("Firefox: Firefox send requested form.");
	}
	
	public String generateName() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm");
		String name = dateFormat.format(new Date())+ "_name";
		return name;
	}
	
	public String generateEmail() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm");
		String email = dateFormat.format(new Date())+ "@mail.com";
		return email;
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
			print("Firefox: doesn't found selector.");
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
		print("Firefox: clicked on shared devices");
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
		System.out.println("Firefox: Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}

	public void resizeWindow(int width, int height) {
		firefox.manage().window().setSize(new Dimension(width, height));
	}

	public void reloadAgentUrl() {
		firefox.get("http://blank.org/");
		firefox.get("https://videome.leadsecure.com/testtes");
	}
	
	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(firefox, 30);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) firefox).executeScript("return document.readyState").equals("complete");
			}
		});
		print("Firefox: Page is loaded.");
	}

	@AfterTest
	void close() {
		if (firefox != null) {
			firefox.quit();
			print("Firefox is closed");
		}
	}	
}
