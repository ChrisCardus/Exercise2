package part2.behaviours;

import lejos.robotics.subsumption.Behavior;
import part2.Path;
import rp.Robot;

public class Junction implements Behavior {

	private Robot robot;
	private boolean suppressed;
	private Path path;
	
	public Junction(Robot robot, Path path) {
		this.robot = robot;
		this.path = path;
		suppressed = false;
	}

	@Override
	public boolean takeControl() {
		//Returns true if both light sensors are over black lines.
		return (robot.isOnLeft() && robot.isOnRight());
	}

	@Override
	public void action() {
		suppressed = false;
		//Executes the next instruction found in the path.
		path.changeHeading();
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
