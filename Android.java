package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

@SuppressWarnings("rawtypes")

public class Android {

	AppiumDriver android;
	Firefox firefox;
	By prospector = By.id("ongoingCallerName");
	By visitorName = By.id("txtTitle");
	By messages = By.id("chatRowInMessageText");
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "009511b51b8a18eb");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.leadsecure.agent");
		capabilities.setCapability("appActivity", "com.leadsecure.core.ui.LoginActivity");
		android = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// android.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firefox = new Firefox();
	}

	void startConversation() {
		print("Android: Waiting up to 20 sec. for visitors");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingCallerName")));
		List<WebElement> visitors = android.findElements(By.id("ongoingCallerName"));
		for (WebElement webElement : visitors) {
			print("Android: You have visitor with name: " + webElement.getText());
		}
		visitors.get(0).click();
		try {
			String visitorName = android.findElement(By.id("txtTitle")).getText();
			print("Started Conversation with " + visitorName);
		} catch (Exception e) {
			print("Android: Error on get visitor's name ");
		}

	}

	void closeConversation() {
		clickOnIdIfIsPresent("btnLeft");
		print("Android closed conversation");

	}

	void sendMessage(String message) {
		WebElement textField = android.findElement(By.id("chatBottomLayout"));
		textField.click();
		android.hideKeyboard();
		print("Android clicked in message field.");
		textField.clear();
		print("Android cleared message field.");
		textField.sendKeys(message);
		clickOnNameIfIsPresent("Send");
		print("Android: Message sent.");
	}

	void verifyMessage(String message) {
		List<WebElement> messages = android.findElements(By.id("chatRowInMessageText"));
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

	void startVideoCall() {
		clickOnIdIfIsPresent("btnRight");
		print("Android clicked on StartVideoCall button.");
	}

	void stopOrRejectVideoCall() {
		clickOnIdIfIsPresent("incallRejectButton");
		print("Android clicked on StopVideoCall button.");
	}

	void answerVideoCall() {
		if (isElementPresent(By.id("incomingCallerName"))) {
			String callerName = android.findElement(By.id("incomingCallerName")).getText();
			print(callerName + " is calling");
		} else {
			print("Android: Greshka pri get caller name");
		}
		clickOnIdIfIsPresent("incallAcceptButton");
		print("Android answered video call");
	}

	void changeCameraTo(String cameraPosition){
		String optionName = null;
		//Camera position can be "Back Camera","Front Camera" or "No Camera" 
		cameraPosition = cameraPosition.toLowerCase();
		List<WebElement> options = android.findElements(By.xpath("*//android.widget.CheckedTextView"));
		for (int j = 0; j < options.size(); j++) {
			optionName = options.get(j).getText().toLowerCase();
			print(j + "- " + optionName);
			if (Objects.equals(optionName, cameraPosition)) {
				options.get(j).click();
				break;
			}
//			if (Objects.equals(new String("back camera"), cameraPosition)) {
//				options.get(0).click();
//				print("Clicked on back camera");
//			}
//			if (Objects.equals(new String("front camera"), cameraPosition)) {
//				options.get(1).click();
//				print("Clicked on front camera");
//			}
//			if (Objects.equals(new String("no camera"), cameraPosition)) {
//				options.get(2).click();
//				print("Clicked on 'no camera'");
//			}
			
		}
		print("Android changed camera to: " + optionName);
	}
	
	
	void openNotifications() throws InterruptedException {
		((AndroidDriver) android).openNotifications();
		pause(2);
		print("Android opened Notifications.");
	}

	void clearNotifications() throws InterruptedException {
		// Required Login
		openNotifications();
		Boolean isPresent = android.findElements(By.className("android.widget.Button")).size() > 0;
		if (isPresent) {
			List<WebElement> allButtons = android.findElements(By.className("android.widget.Button"));
			try {
				pause(2);
				allButtons = android.findElements(By.className("android.widget.Button"));
				print("allButtons.size() = " + allButtons.size());
				for (int i = 0; i < allButtons.size(); i++) {
					String buttonName = allButtons.get(i).getText();
					print("Android: Found button " + i + " with name= " + buttonName);
					if (Objects.equals(buttonName, new String(""))) {
						print("Android: Found clear notification button");
						allButtons.get(i).click();
						print("Android: Clear notifications.");
						return;
					} else {
						print("Android: This button is not clear notification button");
					}
				}
			} catch (Exception e) {
				print("Android: There are no any notifications");
				pressBackButton();
			}
		} else {
			print("Android: There are no any notifications");
			pressBackButton();
		}
	}

	void inVideoCallCameraButtonClick() {
		clickOnIdIfIsPresent("incallCameraButton");
		print("Android clicked on incallCameraButton button.");
	}

	void verifyStoppedOwnVideo() throws IOException{
				if (isElementPresent(By.id("incallCalleeImage"))) {
					print("Android: Video is stopped");
				}
	}
	
	void verifyStoppedVideoFromProspector() throws IOException{
		if (isElementPresent(By.id("incallCallerImage"))) {
			print("Android: Video from prospector is stopped");
		}
	}
	
	void requestPhoneAndEmail() throws IOException{
		clickOnIdIfIsPresent("requestInfoButton");
		print("Android: Requested prospctor for phone and email");
	}
	
	void videoCallGetTextFromElements() {
		WebDriverWait wait = new WebDriverWait(android, 25);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
		List<WebElement> list = android.findElements(By.className("android.widget.TextView"));
		int i = 0;
		for (WebElement webElement : list) {
			try {
				print("Android: Text from element " + i + "= " + webElement.getText());
				i++;
			} catch (Exception e) {
				print("Android: Greshka pri webElement.getText()");
			}
		}
	}

	void login(String email, String password) {
		print("Proceed with android login.");
		clickOnIdIfIsPresent("loginArrow");
		android.findElement(By.name("Email")).sendKeys(email);
		android.findElement(By.id("signInPasswordEdit")).sendKeys(password);
		clickOnNameIfIsPresent("ENTER");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("vanityTitleText")));
		print("Android: You are logged in from android device");
	}

	void logout() throws InterruptedException {
		print("Proceed with logout.");
		// Navigate to logout and logout
		clickOnIdIfIsPresent("vanityMenuButton");
		clickOnNameIfIsPresent("Logout");
		clickOnNameIfIsPresent("Yes");
	}

	private WebElement getWebElement(By selector) {
		try {
			WebElement element = android.findElement(selector);
			return element;
		} catch (Exception e) {
			WebElement element = android.findElement(selector);
			return element;
		}
	}

	private boolean isElementPresent(By selector) {
		boolean found = true;
		try {
			WebDriverWait wait = new WebDriverWait(android, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		} catch (NoSuchElementException e) {
			print("Android: doesn't found selector");
			found = false;
		}
		return found;
	}

	private void typeTextInSelector(By selector, String text) {
		try {
			android.findElement(selector).click();
			android.findElement(selector).sendKeys(text);
		} catch (NoSuchElementException e) {
		}
	}

	private void clickOnSelector(By selector) throws InterruptedException {
		WebElement element = getWebElement(selector);
		try {
			element.click();
		} catch (WebDriverException toci) {
			pause(5);
			getWebElement(selector);
		}
	}

	private void clickOnIdIfIsPresent(String id) {
		Boolean isPresent = android.findElements(By.id(id)).size() > 0;
		if (isPresent) {
			android.findElement(By.id(id)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			android.findElement(By.id(id)).click();
		}
	}

	private void clickOnNameIfIsPresent(String name) {
		Boolean isPresent = android.findElements(By.name(name)).size() > 0;
		if (isPresent) {
			android.findElement(By.name(name)).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			android.findElement(By.name(name)).click();
		}
	}

	void lockScreen() throws IOException {
			//adbExecuteComand("adb shell am force-stop io.appium.unlock");
			adbExecuteComand("adb shell input keyevent 26");
			print("Android: Screen is locked");
	}

	void pressHomeButton() {
		((AndroidDriver) android).pressKeyCode(AndroidKeyCode.HOME);
		print("Android pressed Home button - app works in background");
	}

	void pressBackButton() {
		((AndroidDriver) android).pressKeyCode(AndroidKeyCode.BACK);
		print("Android pressed back button.");
	}

	void acceptRejectNotification(String acceptOrReject) {
		acceptOrReject = acceptOrReject.toLowerCase();
		List<WebElement> buttons = android.findElements(By.xpath("*//android.widget.Button"));
		String buttonName = null;
		for (int j = 0; j < buttons.size(); j++) {
			buttonName = buttons.get(j).getText().toLowerCase();
			if (Objects.equals(buttonName, acceptOrReject)) {
				buttons.get(j).click();
				return;
			}
		}
		Assert.assertEquals(buttonName, acceptOrReject);
		print("Android accepted/rejected notification");
	}

	void getAllNotifications() {
		List<WebElement> elements = android.findElements(By.xpath("//android.widget.TextView"));
		for (WebElement webElement : elements) {
			print(webElement.getText());
		}
		print("elements.size()" + Integer.toString(elements.size()));
		elements = android.findElements(By.id("title"));
		for (WebElement webElement : elements) {
			print(webElement.getText());
		}
		print("elements.size()" + Integer.toString(elements.size()));

		String title;
		int i;
		for (i = 0; i < elements.size(); i++) {
			title = elements.get(i).getText();
			if (Objects.equals(title, new String("Video Agent"))) {
				print("Found video agent notification.");
				return;
			} else {
				print("There are no agent notifications");
			}
		}
	}

	void runAppInBackground(int seconds) {
		print("Android: App is in background for " + seconds + " seconds");
		android.runAppInBackground(seconds);
	}

	void getAppBackInForeground() {
		android.runAppInBackground(1);
		print("Android: App is in foreground now");
	}

	void startApp() {
		try {
			android.launchApp();
		} catch (Exception e) {
			// Ignore "... but it's already started" error.
		}
		print("Android launched application.");
	}

	void startAgentAppMainActivity() throws IOException {
		adbExecuteComand("adb shell monkey -p com.leadsecure.agent -c android.intent.category.LAUNCHER 1");
		print("Android 'adb shell monkey' launched video agent application.");
	}
	void startCalculatorApp() throws IOException {
		//kill calculator before start
		adbExecuteComand("adb shell am force-stop com.google.android.calculator");
		adbExecuteComand("adb shell am force-stop com.android.calculator2");
		adbExecuteComand("adb shell am force-stop com.google.android.calculator-2");
		
		adbExecuteComand("adb shell monkey -p com.google.android.calculator -c android.intent.category.LAUNCHER 1");
		adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
		adbExecuteComand("adb shell am start -n com.android.calculator2/.CalculatorGoogle");
		adbExecuteComand("adb shell monkey -p com.google.android.calculator-2 -c android.intent.category.LAUNCHER 1");
		print("Android 'adb' launched Calculator application.");
	}
	
	void closeApp() throws IOException, InterruptedException {
		((AppiumDriver)android).closeApp();
		//adbExecuteComand("adb shell input keyevent 187");
		print("Android closed application.");
	}

	void unlockScreen() throws IOException {
			adbExecuteComand("adb shell input keyevent 82");
			print("Android unlocked screen.");
	}

	void unlockScreenWithAppium() throws InterruptedException, IOException {
			adbExecuteComand("adb shell am start -n io.appium.unlock/.Unlock");
			print("Android: Screen is unlocked by Appium");
	}
	
	void print(String text) {
		System.out.println(text);
	}

	void adbExecuteComand(String command) throws IOException {
		Runtime.getRuntime().exec(command);
		print("Android executed command: " + command);
	}

	void pause(int seconds) throws InterruptedException {
		System.out.println("Android: Waiting " + seconds + " seconds");
		Thread.sleep(seconds * 1000);
	}
	
	void quit() {
		android.quit();
	}

	@AfterClass
	void cleanUpAndroid() throws IOException {
		print("Android: Clean up.");
		adbExecuteComand("adb shell input keyevent 26");
		adbExecuteComand("adb shell am force-stop io.appium.unlock");
		adbExecuteComand("adb shell am force-stop com.leadsecure.agent");
		adbExecuteComand("adb shell pm clear com.leadsecure.agent");
		
		adbExecuteComand("adb shell am force-stop com.google.android.calculator");
		adbExecuteComand("adb shell am force-stop com.android.calculator2");
		adbExecuteComand("adb shell am force-stop com.google.android.calculator-2");
	}
}