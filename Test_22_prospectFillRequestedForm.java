package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_22_prospectFillRequestedForm {

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

	@Test(priority = 22)
	public void prospectFillRequestedForm() throws InterruptedException, IOException {
		android.print("start new test  ------------------------------------------------------------------");
		android.startConversation();
		android.startVideoCall();
		android.pause(1);
		firefox.answerVideoCall();
		firefox.muteMicrophone();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.pause(1);
		android.requestPhoneAndEmail();
		android.pause(5);
		firefox.fillRequestedForm(firefox.generateName(), firefox.generateEmail(), "1234567890");
		// ToDo verify requested details

		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - prospect fill details in requested form from agent.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}