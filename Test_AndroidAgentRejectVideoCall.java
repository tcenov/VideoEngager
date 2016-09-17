package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_AndroidAgentRejectVideoCall {

	Firefox firefox = new Firefox();
	Android android = new Android();

	
	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
	}

	@Test(priority = 2)
	public void firefoxLogin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.login();
	}

	@Test(priority = 7)
	public void androidAgentRejectVideoCall() throws InterruptedException {
		firefox.callButtonFromHomeClick();
		android.pause(3);
		android.stopOrRejectVideoCall();
		android.pause(3);
		android.print("Test case - android agent rejected video call.");
	}
 
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
