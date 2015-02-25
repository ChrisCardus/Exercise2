package part3.behaviours;

import lejos.robotics.subsumption.Behavior;
import rp.Robot;

public class Turn implements Behavior {

	private Boolean suppressed;
	private Robot chen;
	private int direction;

	public Turn(Robot robot) {
		suppressed = false;
		chen = robot;
		direction = 0;
	}

	@Override
	public boolean takeControl() {
		for (int i = 0; i < chen.numberOfObjects(); i++) {
			direction = chen.getRectangle(i).x;
			if (chen.getRectangle(i).x > 120 || chen.getRectangle(i).x < 30) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void action() {
		suppressed = false;
		System.out.println("Turn    " + direction);
		if (direction < 30) {
			chen.correctLeft();
		} else if (direction > 70) {
			chen.correctRight();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
