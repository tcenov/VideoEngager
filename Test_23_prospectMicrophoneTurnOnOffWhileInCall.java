package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_23_prospectMicrophoneTurnOnOffWhileInCall {

	Firefox firefox = new Firefox();
	Android android = new Android();
	
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

	@Test(priority = 23)
	public void prospectMicrophoneTurnOnOffWhileInCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.callButtonFromHomeClick();
		// firefox.callButtonFromConversationClick();

		android.answerVideoCall();
		android.pause(1);
		firefox.muteMicrophone();
		android.pause(1);
		firefox.unmuteMicrophone();
		android.pause(1);
		firefox.muteMicrophone();
		android.pause(1);
		firefox.unmuteMicrophone();
		
		android.stopOrRejectVideoCalling();
		android.pause(1);
		android.print("Test case - prospectMicrophoneTurnOnOffWhileInCall");
		android.print("---------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
