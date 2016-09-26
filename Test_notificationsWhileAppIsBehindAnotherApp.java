package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

//@Listeners({ ScreenshotUtility.class })
public class Test_notificationsWhileAppIsBehindAnotherApp {

	Firefox firefox = new Firefox();
	static Android android = new Android();
	
	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 2)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.join();
		android.print("--------------------------------------------------------------------------------");
	}


	@Test(priority = 11)
	public void notificationsWhileAppIsBehindAnotherAppAndDeviceLocked() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
//		firefox.close();
//		firefox.setUp();
//		firefox.waitForPageLoad();
		android.startCalculatorApp();
		android.lockScreen();
		android.pause(5);
		firefox.SendMessage("Message while android is locked and behind calculator");
		android.pause(5);
		//android.unlockScreenWithAppium();
		android.unlockScreen();
		android.startCalculatorApp();
		//android.getAppBackInForeground();
		android.openNotifications();
		android.openNotifications();
		android.getAllNotifications();
		android.pause(2);
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android is locked and behind calculator");
		android.closeConversation();
		android.print("Test case - chat notifications while device is locked.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
