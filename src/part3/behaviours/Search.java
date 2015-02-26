package part3.behaviours;

import lejos.robotics.subsumption.Behavior;
import rp.Robot;

public class Search implements Behavior {

	private Boolean suppressed;
	private Robot chen;

	public Search(Robot robot) {
		suppressed = false;
		chen = robot;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		chen.correctLeft();
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
