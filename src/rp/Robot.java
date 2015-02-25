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
	private boolean edge;
	private boolean isDirectionLeft;

	public Robot() {
		this.pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.B, false);
		this.lightRight = new LightSensor(SensorPort.S1);
		this.lightLeft = new LightSensor(SensorPort.S2);
		this.ultra = new UltrasonicSensor(SensorPort.S3);
		this.cam = new NXTCam(SensorPort.S4);
		edge = false;
	}

	public void setInit(int l, int r) {
		this.leftInit = l;
		this.rightInit = r;
	}

	public int getLeftInit() {
		return leftInit;
	}

	public int getRightInit() {
		return rightInit;
	}

	public float getRange() {
		return ultra.getRange();
	}

	public boolean isOnLeft() {
		return getLightLeft() < (getLeftInit() - 3);
	}

	public boolean isOnRight() {
		return getLightRight() < (getRightInit() - 3);
	}

	public void move(int travelSpeed) {
		if (travelSpeed > 0) {
			pilot.setTravelSpeed(travelSpeed);
			pilot.forward();
		} else {
			System.out.println(travelSpeed);
			pilot.setTravelSpeed(-travelSpeed);
			pilot.backward();
		}
	}

	public void travel(int distance) {
		pilot.travel(distance);
	}

	public void followLine() {
		if ((getLeftInit() - getLightLeft()) > (getRightInit() - getLightRight())) {
			if (getLightLeft() < (getLeftInit() - 3)) {
				correctLeft();
			} else {
				move(4);
			}
		} else {
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

	public int getLightLeft() {
		return lightLeft.readValue();
	}

	public int getLightRight() {
		return lightRight.readValue();
	}

	public void correctLeft() {
		pilot.arc(0, -3, true);
	}

	public void correctRight() {
		pilot.arc(0, 3, true);
	}

	public void lightsOn() {
		lightRight.setFloodlight(true);
		lightLeft.setFloodlight(true);
	}

	public void turn(int direction) {
		pilot.travel(3);

		if (direction == 2) {
			pilot.rotate(-90);
		} else if (direction == 1) {
			pilot.rotate(90);
		}
	}

	public void track() {
		cam.setTrackingMode(NXTCam.COLOR);
		cam.sortBy(NXTCam.SIZE);
		cam.enableTracking(true);
	}

	public int numberOfObjects() {
		return cam.getNumberOfObjects();
	}

	public Rectangle getRectangle(int i) {
		return cam.getRectangle(i);
	}
}