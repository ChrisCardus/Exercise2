package part2;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;
import part1.Correct;
import part2.behaviours.Junction;
import part3.behaviours.Forward;
import rp.Robot;
import rp.RobotProgrammingDemo;

public class GridNavigator extends RobotProgrammingDemo {

	public GridNavigator() {
		super();
	}

	@Override
	public void run() {
		Robot robot = new Robot();
		
		robot.lightsOn();
		Delay.msDelay(3000);
		robot.setInit(robot.getLightLeft(), robot.getLightRight());
		
		ArrayList<Integer> p = new ArrayList<Integer>();
		p.add(Robot.STRAIGHT);
		p.add(Robot.RIGHT);
		p.add(Robot.LEFT);
		p.add(Robot.RIGHT);
		p.add(Robot.LEFT);
		
		Path path = new Path(robot, p);
		
		Behavior forward = new Forward(robot);
		Behavior junction = new Junction(robot, path);
		Behavior correct = new Correct(robot);
		Behavior[] bArray = {forward, correct, junction};
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
