package part2;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;
import part1.Correct;
import part2.behaviours.Junction;
import rp.Robot;
import rp.RobotProgrammingDemo;

public class GridNavigator extends RobotProgrammingDemo {

	public GridNavigator() {
		super();
	}

	@Override
	public void run() {
		Robot robot = new Robot();
		
		//Gives the lights time to turn on and ensures the robot is still.
		robot.lightsOn();
		Delay.msDelay(3000);
		
		//Sets the initial values of both the light sensors.
		robot.setInit(robot.getLightLeft(), robot.getLightRight());
		
		//Creates an array to store a list of instructions for the robot.
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(Robot.STRAIGHT);
		p.add(Robot.RIGHT);
		p.add(Robot.LEFT);
		p.add(Robot.RIGHT);
		p.add(Robot.LEFT);
		
		//Creates a path using the instructions.
		Path path = new Path(robot, p);
		
		//Creates an arbitrator that will move the robot forward by default
		//then correct itself if either light sensor is over a line but the other isn't
		//and turns at a junction based on the next instruction in the path.
		Behavior junction = new Junction(robot, path);
		Behavior correct = new Correct(robot);
		Behavior[] bArray = {correct, junction};
		Arbitrator arby = new Arbitrator(bArray);
		arby.start();
	}
	
	public static void main(String[] args){
		RobotProgrammingDemo demo = new GridNavigator();
		System.out.println("GridNavigator");
		System.out.println("Push button to start");
		Button.waitForAnyPress();
		demo.run();
	}

}
