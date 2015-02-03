package part1;

import lejos.nxt.Button;
import rp.Robot;
import rp.RobotProgrammingDemo;

public class FeedbackControl extends RobotProgrammingDemo {

	public static void main(String[] args) {
		RobotProgrammingDemo demo = new FeedbackControl();
		System.out.println("Please press the orange button to start");
		Button.waitForAnyPress();
		System.out.print("Please press the escape button to stop.");
		demo.run();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Robot chen = new Robot();

		float desired = 15.0f;
		
		while(m_run){
			float current = chen.getRange();
			float error = current - desired;
			chen.move((int)error);
		}
	}

}
