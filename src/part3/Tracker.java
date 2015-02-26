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
		
		//Initialises the NXTCam and turns the tracking on.
		chen.track();

		//Creates an arbitrator that will rotate the robot to search for the ball by default
		//then will move the robot forwards when it detects the ball.
		//If the ball moves into the peripheral vision of the robot
		//it will turn until the ball is back in the centre.
		//Finally it will stop when it is close to the ball. 
		Behavior search = new Search(chen);
		Behavior forward = new Forward(chen);
		Behavior stop = new Stop(chen);
		Behavior turn = new Turn(chen);
		Behavior[] bArray = { search, forward, turn, stop };
		Arbitrator arby = new Arbitrator(bArray);
		arby.start();
	}

	public static void main(String[] args) {
		System.out.println("Tracker");
		System.out.println("Push button to start");
		Button.waitForAnyPress();
		RobotProgrammingDemo demo = new Tracker();
		demo.run();
	}

}
