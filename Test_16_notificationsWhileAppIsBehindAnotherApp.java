package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_16_notificationsWhileAppIsBehindAnotherApp {

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

@Test(priority = 17)
public void notificationsWhileAppIsBehindAnotherApp() throws InterruptedException, IOException, AWTException {
	android.print("start new test ------------------------------------------------------------------");
//	firefox.close();
//	firefox.setUp();
//	firefox.waitForPageLoad();
	android.startCompassApp();
	android.pause(5);
	firefox.SendMessage("Message while android is behind calculator");
	android.pause(5);
	android.openNotifications();
	android.acceptRejectNotification("accept");
	android.pause(4);
	android.verifyMessage("Message while android is behind calculator");
	android.closeConversation();
	android.print("Test case - chat notifications while another app is on focus.");
	android.print("--------------------------------------------------------------------------------");
}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
