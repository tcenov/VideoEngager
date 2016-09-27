package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_04_androidAgentReceiveVideoCall {

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
	
	@Test(priority = 6)
	public void androidAgentReceiveVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.pause(2);
		firefox.callButtonFromHomeClick();
		//firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		//ToDo verify video
		android.pause(5);
		firefox.muteMicrophone();
		android.stopOrRejectVideoCall();
		android.pause(2);
		android.print("Test case - android agent receive video call,then android end video call ");
		android.print("---------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
