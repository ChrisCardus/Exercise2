package rp;

import java.awt.Rectangle;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.NXTCam;
import lejos.robotics.RangeFinder;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {

	public final static int STRAIGHT = 0;
	public final static int RIGHT = 1;
	public final static int LEFT = 2;
	private DifferentialPilot pilot;
	private LightSensor lightRight;
	private LightSensor lightLeft;
	private RangeFinder ultra;
	private NXTCam cam;
	private int leftInit;
	private int rightInit;

	public Robot() {
		this.pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.B, false);
		this.lightRight = new LightSensor(SensorPort.S1);
		this.lightLeft = new LightSensor(SensorPort.S2);
		this.ultra = new UltrasonicSensor(SensorPort.S3);
		this.cam = new NXTCam(SensorPort.S4);
	}

	//Sets the initial light values of both light sensors.
	public void setInit(int l, int r) {
		this.leftInit = l;
		this.rightInit = r;
	}

	//Returns the initial value of the left light sensor.
	public int getLeftInit() {
		return leftInit;
	}

	//Returns the initial value of the right light sensor.
	public int getRightInit() {
		return rightInit;
	}
	
	//Returns the current distance between the robot and the object in front of it.
	public float getRange() {
		return ultra.getRange();
	}

	//Checks if the left light sensor is on the line.
	public boolean isOnLeft() {
		return getLightLeft() < (getLeftInit() - 3);
	}

	//Checks if the right light sensor is on the line.
	public boolean isOnRight() {
		return getLightRight() < (getRightInit() - 3);
	}

	//Moves the robot forward at a given speed. 
	public void move(int travelSpeed) {
		//Checks that the travelSpeed is positive.
		if (travelSpeed > 0) {
			pilot.setTravelSpeed(travelSpeed);
			pilot.forward();
		} else {
			System.out.println(travelSpeed);
			pilot.setTravelSpeed(-travelSpeed);
			pilot.backward();
		}
	}

	//Moves the robot forward a given number of rotations.
	public void travel(int distance) {
		pilot.travel(distance);
	}

	public void followLine() {
		//Check which sensor has the greatest amount of change from the initial value
		//to avoid giving priority to either sensor.
		if ((getLeftInit() - getLightLeft()) > (getRightInit() - getLightRight())) {
			//Checks if the left light sensor is on the line.
			if (getLightLeft() < (getLeftInit() - 3)) {
				correctLeft();
			} else {
				move(4);
			}
		} else {
			//Checks if the right light sensor is on the line.
			if (getLightRight() < (getRightInit() - 3)) {
				correctRight();
			} else {
				move(4);
			}
		}
	}

	public void stop() {
		pilot.stop();
	}

	//Returns the value given by the left light sensor.
	public int getLightLeft() {
		return lightLeft.readValue();
	}

	//Returns the value given by the right light sensor.
	public int getLightRight() {
		return lightRight.readValue();
	}

	//Rotates the robot to the left to place the line back between the two light sensors.
	public void correctLeft() {
		pilot.arc(0, -3, true);
	}

	//Rotates the robot to the right to place the line back between the two light sensors.
	public void correctRight() {
		pilot.arc(0, 3, true);
	}

	//Turns the lights on the light sensors on.
	public void lightsOn() {
		lightRight.setFloodlight(true);
		lightLeft.setFloodlight(true);
	}

	//Turns the robot 90 degrees in a given direction.
	public void turn(int direction) {
		//Moves the robot so that the wheels are on the black line.
		pilot.travel(3);

		//Checks if direction is left or right and turns accordingly.
		if (direction == LEFT) {
			pilot.rotate(-90);
		} else if (direction == RIGHT) {
			pilot.rotate(90);
		}
	}

	//Initialises the NXTCam and turns the tracking on.
	public void track() {
		cam.setTrackingMode(NXTCam.COLOR);
		cam.sortBy(NXTCam.SIZE);
		cam.enableTracking(true);
	}

	//Returns an integer containing the number of objects the robot can see.
	public int numberOfObjects() {
		return cam.getNumberOfObjects();
	}

	//Returns the ith rectangle that the robot can see.
	public Rectangle getRectangle(int i) {
		return cam.getRectangle(i);
	}
}