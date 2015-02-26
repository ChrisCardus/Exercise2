package part1;

import lejos.robotics.subsumption.Behavior;
import rp.Robot;

public class Correct implements Behavior {
	private boolean suppressed = false;
	private Robot robot;
	
	public Correct(Robot robot) {
		this.robot = robot;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;

		robot.followLine();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
