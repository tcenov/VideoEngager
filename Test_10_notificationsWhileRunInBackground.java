package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_10_notificationsWhileRunInBackground {

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

	@Test(priority = 9)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		//android.runAppInBackground(5); - this is not a solution.
		android.pressHomeButton();
		android.clearNotifications();
//		firefox.close();
//		firefox.setUp();
//		firefox.waitForPageLoad();
		firefox.SendMessage("Message while android works in background");
		android.openNotifications();
		android.getAllNotifications();
		android.pause(2);
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android works in background");
		android.pause(2);
		android.closeConversation();
		android.print("Test case - chat notifications while android run in background");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
