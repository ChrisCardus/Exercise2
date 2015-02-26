package part3.behaviours;

import lejos.robotics.subsumption.Behavior;
import rp.Robot;

public class Stop implements Behavior {
	
	private Robot chen;
	private Boolean suppressed;
	
	public Stop(Robot robot){
		chen = robot;
		suppressed = false;
	}
	@Override
	public boolean takeControl() {
		//Loops through all the objects it can see.
		for(int i = 0; i < chen.numberOfObjects(); i++){
			//Checks if the width of rectangle i is less than 20.
			if (chen.getRectangle(i).width < 20){
				return true;
			} else {
				return false;
			}
		}
		//Returns false if the robot cannot see any objects.
		return false;
	}

	@Override
	public void action() {
		suppressed = false;
		chen.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
