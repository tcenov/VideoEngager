package android2.VideoEngager;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Minimize {

	public void minimize() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_N);
	}
}
