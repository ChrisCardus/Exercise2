package part3;

import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import part3.behaviours.Forward;
import part3.behaviours.Search;
import part3.behaviours.Stop;
import part3.behaviours.Turn;
import rp.Robot;
import rp.RobotProgrammingDemo;

public class Tracker extends RobotProgrammingDemo {

	public Tracker() {
	}

	@Override
	public void run() {
		Robot chen = new Robot();
		chen.track();

		Behavior search = new Search(chen);
		Behavior forward = new Forward(chen);
		Behavior stop = new Stop(chen);
		Behavior turn = new Turn(chen);

		Behavior[] bArray = { search, forward, turn, stop };
		Arbitrator arby = new Arbitrator(bArray);
		arby.start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Button.waitForAnyPress();
		RobotProgrammingDemo demo = new Tracker();
		demo.run();
	}

}
