package rp;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
	
	private DifferentialPilot pilot;
	private LightSensor lightRight;
	private LightSensor lightLeft;
	private RangeFinder ultra;
	private int leftInit;
	private int rightInit;
	
	public Robot() {
		this.pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.B,
				false);
		this.lightRight = new LightSensor(SensorPort.S1);
		this.lightLeft = new LightSensor(SensorPort.S2);
		this.ultra = new UltrasonicSensor(SensorPort.S3);
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
	
	public float getRange(){
		return ultra.getRange();
	}
	
	public void move(int travelSpeed){
		if(travelSpeed > 0)
		{
			pilot.setTravelSpeed(travelSpeed);
			pilot.forward();
		} else {
			pilot.setTravelSpeed(-travelSpeed);
			pilot.backward();
		}
	}
	
	public void followLine(){
		if((getLeftInit() - getLightLeft()) > (getRightInit() - getLightRight())){
			if(getLightLeft() < (getLeftInit() - 3)) {
				correctLeft();
			} else {
				move(2);
			} 
		} else {
			if(getLightRight() < (getRightInit() - 3)) {
				correctRight();
			} else {
				move(2);
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

	public void correct(int i) {
		pilot.arc(0, (double)i);
	}
	
	public void lightsOn(){
		lightRight.setFloodlight(true);
		lightLeft.setFloodlight(true);
	}

	public void turn(boolean left) {
		if(left){
			pilot.travel(3);
			pilot.rotate(90);
		} else {
			pilot.travel(3);
			pilot.rotate(-90);
		}
	}
}