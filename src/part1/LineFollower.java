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
		
		robot.lightsOn();
		Delay.msDelay(3000);
		
		robot.setInit(robot.getLightLeft(), robot.getLightRight());
		
		while(m_run) {
			robot.followLine();
		}
	}

	public static void main(String[] args) {
		RobotProgrammingDemo demo = new LineFollower();
		System.out.println("Please press the orange button to start");
		Button.waitForAnyPress();
		System.out.println("Please press the escape button to stop.");
		demo.run();
	}

}
