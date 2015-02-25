package part2.behaviours;

import rp.Robot;
import lejos.robotics.subsumption.Behavior;

public class JunctionEdge implements Behavior {
	private Robot robot;
	private boolean suppressed;
	
	public JunctionEdge(Robot robot) {
			this.robot = robot;
	}

	@Override
	public boolean takeControl() {
		return robot.getEdge() && ((robot.getDirectionLeft() && robot.isOnLeft()) || (!robot.getDirectionLeft() && robot.isOnRight()));
		//&& (left && leftOnLine || right && rightOnLine)
		//&& the light sensor in the last direction turned is on the line..
		
	}

	@Override
	public void action() {
		System.out.println("edge");
		suppressed = false;
		robot.turn(Robot.STRAIGHT);
		robot.setEdge(false);
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
