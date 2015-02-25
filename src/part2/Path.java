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
		System.out.println("PATH   " + i);
		if(i < headings.size()){
			if(headings.get(i) !=  Robot.STRAIGHT){
				robot.turn(headings.get(i));
			} else {
				robot.travel(3);
			}
			i++;
		} else if(i == headings.size()) {
			robot.stop();
			i++;
		}
	}
}
