package android2.VideoEngager;

import java.awt.AWTException;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class Chrome {
	WebDriver chrome;

	By visitorName_ = By.id("testa");
	By ongoingCallerName_ = By.id("testa");
	By chatMessageField_ = By.id("testa");
	By chatMessages_ = By.id("chatRowInMessageText");
	By callerName_ = By.id("incomingCallerName");
	By inCallCallerName_ = By.id("callerName");
	By inCallCallerEmail_ = By.id("callerEmail");
	By inCallCallerPhone_ = By.id("callerPhone");
	
 
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
		print("Chrome: Proceed with Agent from chrome login.");
		typeTextInSelector(By.name("Email"), email);
		typeTextInSelector(By.name("Password"), password);
		clickOnNameIfIsPresent("ENTER");
		WebDriverWait wait = new WebDriverWait(chrome, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("testa")));
		print("Chrome: You are logged in as agent from Chrome");
	}

	void logout() {
		print("Chrome: Try to logout ");

		print("Chrome: You are logged out from Chrome.");
	}

	void startConversation() {
		print("Chrome: try to startConversation");
		print("Chrome: Waiting up to 15 sec. for visitors");
		WebDriverWait wait = new WebDriverWait(chrome, 15);
		wait.until(ExpectedConditions.elementToBeClickable(ongoingCallerName_));
		List<WebElement> visitors = chrome.findElements(ongoingCallerName_);
		for (WebElement webElement : visitors) {
			print("Chrome: You have visitor with name: " + webElement.getText());
		}
		visitors.get(0).click();
		try {
			String visitorName = chrome.findElement(visitorName_).getText();
			print("Chrome: Started Conversation with " + visitorName);
		} catch (Exception e) {
			print("Chrome: Error on get visitor's name ");
		}
	}

	void closeConversation() {
		print("Chrome: try to closeConversation");
		clickOnIdIfIsPresent("testa");
		print("Chrome closed conversation");
	}

	void sendMessage(String message) {
		print("Chrome: try to sendMessage");
		WebElement textField = chrome.findElement(chatMessageField_);
		textField.click();
		print("Chrome clicked in message field.");
		textField.clear();
		print("Chrome cleared message field.");
		textField.sendKeys(message);
		clickOnNameIfIsPresent("Send");
		print("click: Message sent.");
	}

	void verifyMessage(String message) {
		print("Chrome: try to verifyMessage");
		List<WebElement> messages = chrome.findElements(chatMessages_);
		String messageText = null;
		for (WebElement webElement : messages) {
			messageText = webElement.getText();
			if (Objects.equals(messageText, message)) {
				print("Chrome verified message: " + message);
				return;
			}
		}
		Assert.assertEquals(messageText, message);
	}

	void startVideoCall() {
		print("Chrome: try to startVideoCall");
		clickOnIdIfIsPresent("btnRight");
		print("Chrome clicked on StartVideoCall button.");
	}

	void stopOrRejectVideoCalling() {
		print("Chrome: try to stopOrRejectVideoCalling");
		clickOnIdIfIsPresent("incallRejectButton");
		print("Chrome clicked on StopVideoCall button.");
	}
	
	
	void answerVideoCall() {
		print("Chrome: try to answerVideoCall");
		if (isElementPresent(By.id("incomingCallerName"))) {
			String callerName = chrome.findElement(callerName_).getText();
			print(callerName + " is calling");
		} else {
			print("Chrome: Greshka pri get caller name");
		}
		clickOnIdIfIsPresent("incallAcceptButton");
		print("Chrome answered video call");
	}
	
	void muteOrUnmuteMicrophone() {
		print("Chrome: try to muteOrUnmuteMicrophone");
		clickOnIdIfIsPresent("incallMicrophoneButton");
		print("Chrome: muted or unmuted Mic.");
	}
	
	void changeCameraTo(String cameraPosition) {
		print("Chrome: try to changeCameraTo");
		String optionName = null;
		// Camera position can be "Back Camera","Front Camera" or "No Camera"
		cameraPosition = cameraPosition.toLowerCase();
		List<WebElement> options = chrome.findElements(By.xpath("*//android.widget.CheckedTextView"));
		for (int j = 0; j < options.size(); j++) {
			optionName = options.get(j).getText().toLowerCase();
			print(j + "- " + optionName);
			if (Objects.equals(optionName, cameraPosition)) {
				options.get(j).click();
				break;
			}
			// if (Objects.equals(new String("back camera"), cameraPosition)) {
			// options.get(0).click();
			// print("Android: Clicked on back camera");
			// }
			// if (Objects.equals(new String("front camera"), cameraPosition)) {
			// options.get(1).click();
			// print("Android: Clicked on front camera");
			// }
			// if (Objects.equals(new String("no camera"), cameraPosition)) {
			// options.get(2).click();
			// print("Android: Clicked on 'no camera'");
			// }
		}
		print("Chrome changed camera to: " + optionName);
	}
	
	void inVideoCallCameraButtonClick() {
		print("Chrome: try to inVideoCallCameraButtonClick");
		clickOnIdIfIsPresent("incallCameraButton");
		print("Chrome clicked on incallCameraButton button.");
	}

	void verifyStoppedOwnVideo() {
		print("Chrome: try to verifyStoppedOwnVideo");
		if (isElementPresent(By.id("incallCalleeImage"))) {
			print("Chrome: Verified stopped own video.");
		}
	}

	void verifyStoppedVideoFromProspector() {
		print("Chrome: try to verifyStoppedVideoFromProspector");
		if (isElementPresent(By.id("incallCallerImage"))) {
			print("Chrome: Verified stopped video from prospector.");
		}
	}
	
	void requestPhoneAndEmail() throws IOException {
		print("Chrome: try to requestPhoneAndEmail");
		clickOnIdIfIsPresent("requestInfoButton");
		print("Chrome: Requested prospåctor for phone and email");
	}

	public void verifyRequestedForm(String name, String email, String phone) {
		print("Chrome: started requested form verification.");
		String callName = chrome.findElement(inCallCallerName_).getText();
		String callEmail = chrome.findElement(inCallCallerEmail_).getText();
		String callPhone = chrome.findElement(inCallCallerPhone_).getText();
		print("Chrome = " + callName + " callEmail = " + callEmail + " callPhone = " + callPhone);
		Assert.assertEquals(callName, name);
		print("Chrome: verified name.");
		Assert.assertEquals(callEmail, email);
		print("Chrome: verified email.");
		Assert.assertEquals(callPhone, phone);
		print("Chrome: verified phone.");
		print("Chrome: requested form is verified.");
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
		String name = dateFormat.format(new Date()) + "_name";
		return name;
	}

	public String generateEmail() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm_ss");
		String email = dateFormat.format(new Date()) + "@mail.com";
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