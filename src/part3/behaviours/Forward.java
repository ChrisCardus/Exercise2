package part3.behaviours;

import lejos.robotics.subsumption.Behavior;
import rp.Robot;

public class Forward implements Behavior {
	private boolean suppressed = false;
	private Robot chen;
	
	public Forward(Robot robot) {
		this.chen = robot;
	}

	@Override
	public boolean takeControl() {
		for(int i = 0; i < chen.numberOfObjects(); i++){
			System.out.println("Forward    " + chen.getRectangle(i).width);
			if (chen.getRectangle(i).width >= 20){
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
		chen.move(4);
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}