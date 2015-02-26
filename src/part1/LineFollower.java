package part1;

import lejos.nxt.Button;
import lejos.util.Delay;
import rp.Robot;
import rp.RobotProgrammingDemo;

public class LineFollower extends RobotProgrammingDemo {

	public LineFollower() {
		super();
	}

	@Override
	public void run() {
		Robot robot = new Robot();
		
		//Gives the lights time to turn on and ensures the robot is still.
		robot.lightsOn();
		Delay.msDelay(3000);
		
		//Sets the initial values of both the light sensors.
		robot.setInit(robot.getLightLeft(), robot.getLightRight());
		
		while(m_run) {
			robot.followLine();
		}
	}

	public static void main(String[] args) {
		RobotProgrammingDemo demo = new LineFollower();
		System.out.println("Line Follower");
		System.out.println("Push button to start");
		Button.waitForAnyPress();
		demo.run();
	}

}
