package part1;

import lejos.robotics.subsumption.Behavior;
import rp.Robot;

public class Forward implements Behavior {
	private boolean suppressed = false;
	private Robot robot;
	
	public Forward(Robot robot) {
		this.robot = robot;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		//System.out.println("forward" + robot.getLightLeft() + "    " + robot.getLightRight());

		suppressed = false;
		robot.move(2);
		while(!suppressed) {
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}