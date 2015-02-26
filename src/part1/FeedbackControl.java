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

		//Holds the distance the robot should aim to stay from the wall.
		float desired = 15.0f;
		
		while(m_run){
			//Holds the current distance.
			float current = chen.getRange();
			
			//Calculates the error.
			float error = current - desired;
			
			//Adjusts the movement speed based on the error.
			chen.move((int)(Math.ceil(error / 3.0f)));
		}
	}
}