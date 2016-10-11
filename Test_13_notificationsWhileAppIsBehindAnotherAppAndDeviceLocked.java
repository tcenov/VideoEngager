package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_13_notificationsWhileAppIsBehindAnotherAppAndDeviceLocked {

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
		android.pause(1);
		android.startCompassApp();
		android.pause(3);
		android.lockScreen();
		android.pause(4);
		firefox.SendMessage("Message while android is locked and behind calculator");
		android.pause(5);
		android.unlockScreen();
		android.pause(1);
		android.openNotifications();
		android.openNotifications();
		android.pause(2);
		android.acceptRejectNotification("accept");
		android.pause(5);
		android.verifyMessage("Message while android is locked and behind calculator");
		android.closeConversation();
		android.print("Test case - chat notifications while device is locked.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 12)
	public void notificationsWhileAppIsBehindAnotherAppAndDeviceLocked2() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.startCompassApp();
		android.lockScreen();
		android.pause(5);
		firefox.SendMessage("Message while android is locked and behind calculator");
		android.pause(5);
		android.unlockScreen();
		android.openNotifications();
		android.pause(1);
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android is locked and behind calculator");
		android.closeConversation();
		android.print("Test case - chat notifications while device is locked.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 12)
	public void notificationsWhileAppIsBehindAnotherAppAndDeviceLocked3() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.startCompassApp();
		android.lockScreen();
		android.pause(5);
		firefox.SendMessage("Message while android is locked and behind calculator");
		android.pause(5);
		android.unlockScreen();
		android.openNotifications();
		android.pause(1);
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
