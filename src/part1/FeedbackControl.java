package part1;

import lejos.nxt.Button;
import rp.Robot;
import rp.RobotProgrammingDemo;

public class FeedbackControl extends RobotProgrammingDemo {

	public static void main(String[] args) {
		RobotProgrammingDemo demo = new FeedbackControl();
		System.out.println("Feedback Control");
		System.out.println("Push button to start");
		Button.waitForAnyPress();
		demo.run();

	}

	@Override
	public void run() {
		Robot chen = new Robot();

		float desired = 15.0f;
		
		while(m_run){
			float current = chen.getRange();
			float error = current - desired;
			chen.move((int)(Math.ceil(error / 3.0f)));
		}
	}
}