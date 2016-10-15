package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

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
	By prospector = By.id("ongoingCallerName");
	By visitorName = By.id("txtTitle");
	By messages = By.id("chatRowInMessageText");

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("browserName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.leadsecure.agent");
		capabilities.setCapability("appActivity", "com.leadsecure.core.ui.LoginActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
		cleanUpAndroid();
		android = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		// android.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void startConversation() {
		print("Android: try to startConversation");
		print("Android: Waiting up to 15 sec. for visitors");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingCallerName")));
		List<WebElement> visitors = android.findElements(By.id("ongoingCallerName"));
		for (WebElement webElement : visitors) {
			print("Android: You have visitor with name: " + webElement.getText());
		}
		visitors.get(0).click();
		try {
			String visitorName = android.findElement(By.id("txtTitle")).getText();
			print("Android: Started Conversation with " + visitorName);
		} catch (Exception e) {
			print("Android: Error on get visitor's name ");
		}
	}

	void closeConversation() {
		print("Android: try to closeConversation");
		clickOnIdIfIsPresent("btnLeft");
		print("Android closed conversation");
	}

	void sendMessage(String message) {
		print("Android: try to sendMessage");
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
		print("Android: try to verifyMessage");
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
		print("Android: try to startVideoCall");
		clickOnIdIfIsPresent("btnRight");
		print("Android clicked on StartVideoCall button.");
	}

	void stopOrRejectVideoCalling() {
		print("Android: try to stopOrRejectVideoCalling");
		clickOnIdIfIsPresent("incallRejectButton");
		print("Android clicked on StopVideoCall button.");
	}

	void answerVideoCall() {
		print("Android: try to answerVideoCall");
		if (isElementPresent(By.id("incomingCallerName"))) {
			String callerName = android.findElement(By.id("incomingCallerName")).getText();
			print(callerName + " is calling");
		} else {
			print("Android: Greshka pri get caller name");
		}
		clickOnIdIfIsPresent("incallAcceptButton");
		print("Android answered video call");
	}

	void muteOrUnmuteMicrophone() {
		print("Android: try to muteOrUnmuteMicrophone");
		clickOnIdIfIsPresent("incallMicrophoneButton");
		print("Android: muted or unmuted Mic.");
	}

	void changeCameraTo(String cameraPosition) {
		print("Android: try to changeCameraTo");
		String optionName = null;
		// Camera position can be "Back Camera","Front Camera" or "No Camera"
		cameraPosition = cameraPosition.toLowerCase();
		List<WebElement> options = android.findElements(By.xpath("*//android.widget.CheckedTextView"));
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
		print("Android changed camera to: " + optionName);
	}

	void openNotifications() throws InterruptedException {
		print("Android: try to openNotifications");
		((AndroidDriver) android).openNotifications();
		pause(2);
		print("Android opened Notifications.");
	}

	void clearNotifications() throws InterruptedException {
		print("Android: try to clearNotifications");
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
		print("Android: try to inVideoCallCameraButtonClick");
		clickOnIdIfIsPresent("incallCameraButton");
		print("Android clicked on incallCameraButton button.");
	}

	void verifyStoppedOwnVideo() {
		print("Android: try to verifyStoppedOwnVideo");
		if (isElementPresent(By.id("incallCalleeImage"))) {
			print("Android: Verified stopped own video.");
		}
	}

	void verifyStoppedVideoFromProspector() {
		print("Android: try to verifyStoppedVideoFromProspector");
		if (isElementPresent(By.id("incallCallerImage"))) {
			print("Android: Verified stopped video from prospector.");
		}
	}

	void requestPhoneAndEmail() throws IOException {
		print("Android: try to requestPhoneAndEmail");
		clickOnIdIfIsPresent("requestInfoButton");
		print("Android: Requested prospåctor for phone and email");
	}

	public void verifyRequestedForm(String name, String email, String phone) {
		print("Android: started requested form verification.");
		String callName = android.findElement(By.id("callerName")).getText();
		String callEmail = android.findElement(By.id("callerEmail")).getText();
		String callPhone = android.findElement(By.id("callerPhone")).getText();
		print("callName = " + callName + " callEmail = " + callEmail + " callPhone = " + callPhone);
		Assert.assertEquals(callName, name);
		print("Android: verified name.");
		Assert.assertEquals(callEmail, email);
		print("Android: verified email.");
		Assert.assertEquals(callPhone, phone);
		print("Android: verified phone.");
		print("Android: requested form is verified.");
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
		print("Android: Proceed with logout.");
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
		print("Android: try to enter text");
		try {
			android.findElement(selector).click();
			android.findElement(selector).sendKeys(text);
		} catch (NoSuchElementException e) {
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

	private void clickOnSelectorIfIsPresent(By selector) {
		Boolean isPresent = android.findElements(selector).size() > 0;
		if (isPresent) {
			android.findElement(selector).click();
		} else {
			WebDriverWait wait = new WebDriverWait(android, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
			android.findElement(selector).click();
		}
	}

	void lockScreen() throws IOException {
		print("Android: try to lockScreen");
		// adbExecuteComand("adb shell am force-stop io.appium.unlock");
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
		print("Android: try to acceptRejectNotification");
		acceptOrReject = acceptOrReject.toLowerCase();
		List<WebElement> buttons = android.findElements(By.xpath("*//android.widget.Button"));
		String buttonName = null;
		for (int j = 0; j < buttons.size(); j++) {
			buttonName = buttons.get(j).getText().toLowerCase();
			if (Objects.equals(buttonName, acceptOrReject)) {
				buttons.get(j).click();
				print("Android accepted/rejected notification");
				return;
			}
		}
		Assert.assertEquals(buttonName, acceptOrReject);
		print("Android accepted/rejected notification");
	}

	void getAllNotifications() {
		print("Android: try to getAllNotifications");
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
				print("Android: Found video agent notification.");
				return;
			} else {
				print("Android: There are no agent notifications");
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
		print("Android: try to startApp");
		try {
			android.launchApp();
		} catch (Exception e) {
			// Ignore "... but it's already started" error.
		}
		print("Android launched application.");
	}

	void startAgentAppMainActivity() throws IOException {
		print("Android: try to startAgentAppMainActivity");
		adbExecuteComand("adb shell monkey -p com.leadsecure.agent -c android.intent.category.LAUNCHER 1");
		print("Android 'adb shell monkey' launched video agent application.");
	}

	void startCompassApp() throws IOException {
		print("Android: try to startCompassApp");
		// kill app before start
		adbExecuteComand("adb shell am force-stop com.twodlevel.compass");
		adbExecuteComand("adb shell am start -n com.twodlevel.compass/.MainActivity");

		// // kill calculator before start
		// adbExecuteComand("adb shell am force-stop
		// com.google.android.calculator");
		// adbExecuteComand("adb shell am force-stop com.android.calculator2");
		// adbExecuteComand("adb shell am force-stop
		// com.google.android.calculator-2");
		//
		// adbExecuteComand("adb shell monkey -p com.google.android.calculator
		// -c android.intent.category.LAUNCHER 1");
		// adbExecuteComand("adb shell am start -n
		// com.android.calculator2/.Calculator");
		// adbExecuteComand("adb shell am start -n
		// com.android.calculator2/.CalculatorGoogle");
		// adbExecuteComand("adb shell monkey -p com.google.android.calculator-2
		// -c android.intent.category.LAUNCHER 1");
		// print("Android 'adb' launched Calculator application.");
	}

	void closeApp() throws IOException, InterruptedException {
		print("Android: try to closeApp");
		((AppiumDriver) android).closeApp();
		// adbExecuteComand("adb shell input keyevent 187");
		print("Android closed application.");
	}

	void closeAllApps() throws IOException {
		print("Android: try to closeAllApps");
		adbExecuteComand("adb shell input keyevent 187");
		By closeAnyAppButton = By.xpath("//android.widget.ImageView[contains(@resource-id,'dismiss_task')]");
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(closeAnyAppButton));
		int i = android.findElements(closeAnyAppButton).size();
		while (i > 0) {
			print("view " + i + " running apps.");
			int j = 1;
			List<WebElement> apps = android.findElements(closeAnyAppButton);
			for (WebElement app : apps) {
				try {
					clickOnSelectorIfIsPresent(closeAnyAppButton);
				} catch (Exception e) {
					((AndroidDriver) android).pressKeyCode(AndroidKeyCode.HOME);
					adbExecuteComand("adb shell input keyevent 187");
					clickOnSelectorIfIsPresent(closeAnyAppButton);
				}
				print("closed " + j + " apps");
				j++;
			}
			i = android.findElements(closeAnyAppButton).size();
		}
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
		print("Android: Waiting " + seconds + " seconds");
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

		// adbExecuteComand("adb shell am force-stop
		// com.google.android.calculator");
		// adbExecuteComand("adb shell am force-stop com.android.calculator2");
		// adbExecuteComand("adb shell am force-stop
		// com.google.android.calculator-2");
	}
}