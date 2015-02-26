package part2;

import java.util.ArrayList;

import rp.Robot;

public class Path {
	private Robot robot;
	private ArrayList<Integer> headings;
	private int i;
	
	public Path(Robot robot, ArrayList<Integer> headings) {
		this.headings = headings;
		this.robot = robot;
		this.i = 0;
	}
	
	public void changeHeading(){
		
		//Checks that there are still instructions left in the path.
		if(i < headings.size()){
			
			//Checks that the current instruction is a turn instruction.
			if(headings.get(i) !=  Robot.STRAIGHT){
				//Turns the robot in the direction of the instruction i.
				robot.turn(headings.get(i));
			} else {
				//Moves the robot past the junction before continuing moving forward.
				robot.travel(3);
			}
			//Moves to the next instruction in the path.
			i++;
			//Checks if the robot has reached the end of the path.
		} else if(i == headings.size()) {
			//Stops the robot and iterates the instruction i to prevent an infinite loop.
			robot.stop();
			i++;
		}
	}
}
