package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases extends Android {
	Android base = new Android();
	Firefox firefox = new Firefox();

	@BeforeMethod
	void cleanBeforeEachTest() {
		print("@BeforeMethod");
	}

	@Test(priority=1)
	public void loginSendMessageAndLogout() throws InterruptedException, AWTException, IOException {

		firefox.setUp();
		firefox.join();

		login("tester2006@abv.bg", "Tarator1");
		startConversation();
		sendMessage("Alabala");
		startVideoCall();
		firefox.answerVideoCall();

//		inVideoCallCameraButtonClick();
		pause(2);
		videoCallGetTextFromElements();

		stopOrRejectVideoCall();
//		pause(5);

		closeConversation();
//		logout();
//		pause(20);

		print("Test case - Android login, Firefox login, send message from android, start video call, answerVideoCall from firefox, android stop call, close, logout");
		//firefox.close();
	}

	@Test(priority=2)
	public void agentCameraOnOffBackFrontincall() {
		print("Test case - agent Camera On/ Off/ Back/ Front in call (see update on Local feed, see camera stream to prospect)");	
	}
	
	@Test(priority=3)
	public void prospectCameraOnOffBackFrontInCall () {
		print("Test case - prospect Camera On/ Off/ Back/ Front in call (see update on Local feed, see camera stream to agent) ");		
	}
	
	@Test(priority=4)
	public void agentCameraOnForegroundBackgroundForegroudPauseOnBackground() {
		print("Test case -  agent camera ON foreground/ background/ foregroud:pause on background,  restore camera stream on foreground");		
	}
	
	@Test(priority=5)
	public void prospectCameraOnForegroundBackgroundForegroudPauseOnBackground() {
		print("Test case -  prospect camera ON foreground/ background/ foregroud:pause on background,  restore camera stream on foreground");		
	}
	
	@Test(priority=6)
	public void agentTurnOnMicrophoneWhenNotInCallAndMakeCall() {
		print("Test case - agent Turn On Microphone when no have call and make call (previous state is saved)");		
	}
	
	@Test(priority=7)
	public void agentTurnMicrophoneOnOffWhileInCall() {
		print("Test case -  agent Turn Microphone On / Off when in call");		
	}
	
	@Test(priority=8)
	public void prospectMicrophoneTurnOnWhenNotInCallAndMakeCall () {
		print("Test case -  prospect Microphone Turn On when no have call and make call (previous state is saved)");		
	}
	
	@Test(priority=9)
	public void prospectMicrophoneTurnOnOffWhileInCall() {
		print("Test case -  prospect Microphone Turn On/ Off when in call");		
	}
	
	@Test(priority=10)
	public void agentReceiveCallWhileInAppRejectAccept() {
		print("Test case -  agent receive call while in app/ reject/ accept");		
	}
	
	@Test(priority=11)
	public void agentReceiveCallWhileOnBackgroundRejectAcceptHome() {
		print("Test case -  agent receive call while on background/ reject/ accept (Home o)");		
	}
	
	@Test(priority=12)
	public void agentReceiveCallWhileOnBackgroundRejectAcceptBack() {
		print("Test case -  agent receive call while on background/ reject/ accept (Back <|) : waiting on background more than 5 mins");		
	}
	
	@Test(priority=13)
	public void agentReceiveCallWhileLockDeviceRejectAccept() {
		print("Test case - agent receive call while lock device/ reject/ accept");		
	}
	
	@Test(priority=14)
	public void agentReceiveCallAfterQuitRejectAccept () {
		print("Test case - agent receive call after quit/ reject/ accept ");		
	}
	
	@Test(priority=15)
	public void agentReconnectWhileInCall() {
		print("Test case - agent reconnect while in call");		
	}
	
	@Test(priority=16)
	public void prospectReceiveCallWhileInAppRejectAccept() {
		print("Test case - prospect receive call while in app/ reject/ accept");		
	}
	
	@Test(priority=17)
	public void prospectReconnectWhileInCall() {
		print("Test case - prospect reconnect while in call");		
	}
	
	@Test(priority=18)
	public void agentReceiveChatThenMakeCall() {
		print("Test case - agent receive chat after that call");		
	}
	
	@Test(priority=19)
	public void agentReceiveCallThenSendMessage() {
		print("Test case - agent receive call after that chat");		
	}
	
	@Test(priority=20)
	public void agentRejectChat() {
		print("Test case - agent reject chat");		
	}
	
	@Test(priority=21)
	public void prospectReceiveChatThenMakeCall() {
		print("Test case - prospect receive chat after that call");		
	}
	
	@Test(priority=22)
	public void prospectReceiveCallThenSendMessage() {
		print("Test case - prospect receive call after that chat");		
	}
	
	@Test(priority=23)
	public void a() {
		print("Test case - ");		
	}
	
	@Test(priority=24)
	public void test24() {
		print("Tuk sa opisani test cases!!!");

//			Call			
//			call pick up: one the same agent on 2 devices
//			Chat
//			Group Call
//			call to broker, agent 1 answer the call: stop ringing for the other agents, receive notification that call is accpeted from other
//			call to broker, agent 1 reject the call: continue ringing fot the other agents
//			call to broker, agent 1 reject, agent 2 accept
//			call to broker while the agent on background/ accept/ reject,  receive notification that call is accpeted from other
//			call to broker while agent quite/ accept/ reject
//			call to broker while agent lock device/ accept/reject
//			prospect call to target agent (deep linking)
//			call pick up for group call: one the same agent on 2 devices
//			Two Active Calls
//			active call 1, receive call 2, end/ accept
//			active call 1, receive call 2, hold/ accept
//			active call 1, on background, receive call 2: no have any options into the notification
//			active call 1, on background, receive call 2, missed call 2: call back
//			active call 1, on background, receive call 2, missed call 2: send text
//			resume call
//			Upgrade
//			Upgrade as agent: old version, set location, install new version, see location	
	}
}
