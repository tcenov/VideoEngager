package android2.VideoEngager;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class GetAndroidLogs implements ITestListener {
		
	// This method will execute before starting of Test suite.
	public void onStart(ITestContext tr) {

	}

	// This method will execute, Once the Test suite is finished.
	public void onFinish(ITestContext tr) {

	}

	// This method will execute only when the test is pass.
	public void onTestSuccess(ITestResult tr) {
		
	}

	// This method will execute only on the event of fail test.
	public void onTestFailure(ITestResult tr) {
		try {
			getLogs( tr, "fail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This method will execute before the main test start (@Test)
	public void onTestStart(ITestResult tr) {

	}

	// This method will execute only if any of the main test(@Test) get skipped
	public void onTestSkipped(ITestResult tr) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		
	}

	public void getLogs(ITestResult result, String status) throws IOException {
		Runtime.getRuntime().exec("adb pull \"sdcard/Video Agent/logs\" D:\\temp");
		System.out.println("Pulled logs from android.");

	}
}