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
		for(int i = 0; i < chen.numberOfObjects(); i++){
					System.out.println("Stop   " + chen.getRectangle(i).width);
			if (chen.getRectangle(i).width < 20){
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
		chen.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
