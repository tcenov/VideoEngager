package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_24_agentStartCancelVideoCall {

	Firefox firefox = new Firefox();
	static Android android = new Android();
	
	@Test(priority = 2)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 1)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.join();
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 24)
	public void agentStartCancelVideoCall() throws InterruptedException, AWTException, IOException {
		android.print("--------------------------------------------------------------------------------");
		android.startConversation();
		android.startVideoCall();
		android.wait(1);
		android.stopOrRejectVideoCalling();
		android.wait(1);
		android.startVideoCall();
		android.wait(1);
		android.stopOrRejectVideoCalling();
		android.wait(1);
		android.startVideoCall();
		android.wait(1);
		firefox.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - agentStartCancelVideoCall");
		android.print("---------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}
