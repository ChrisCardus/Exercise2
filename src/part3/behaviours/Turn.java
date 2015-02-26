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
		//Loops through all objects it can see.
		for (int i = 0; i < chen.numberOfObjects(); i++) {
			//sets the direction to the x coordinate of rectangle i.
			direction = chen.getRectangle(i).x;
			//Checks that the x coordinate of rectangle i is not between 30 and 120.
			if (chen.getRectangle(i).x > 120 || chen.getRectangle(i).x < 30) {
				return true;
			} else {
				return false;
			}
		}
		//Returns false if it cannot see any objects.
		return false;
	}

	@Override
	public void action() {
		suppressed = false;
		
		//If the direction is below 30 then the object is on the left.
		if (direction < 30) {
			chen.correctLeft();
		//if the direction is above 70 then the object is on the right.
		} else if (direction > 70) {
			chen.correctRight();
		}

	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
