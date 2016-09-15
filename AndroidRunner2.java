package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;

@SuppressWarnings("rawtypes")

public class AndroidRunner2 extends Android {
	Android base = new Android();
	Firefox firefox = new Firefox();
	AppiumDriver android;

	@Test(priority=1)
	public void login() throws InterruptedException {
		firefox.login();
		login("tester2006@abv.bg", "Tarator1");
		print("krai na testa");
		wait(8);
	}

	@Test(priority=2)
	public void agentRejectChat() {
		print("agent reject chat");
	}
}