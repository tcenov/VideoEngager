package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_16_notificationsWhilePlayGame {

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

@Test(priority = 18)
public void notificationsWhilePlayGame() throws InterruptedException {	
	android.print("start new test ---notificationsWhilePlayGame-----------------------------");
	android.print("At the moment it failed intentionally");
	android.acceptRejectNotification("accept");		
	//ToDo
	android.print("Test case - chat notifications while play game.");
	android.print("--------------------------------------------------------------------------------");
}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
