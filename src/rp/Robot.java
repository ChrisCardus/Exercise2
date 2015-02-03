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
	
	public Robot() {
		this.pilot = new DifferentialPilot(2.1f, 4.4f, Motor.A, Motor.B,
				false);
		this.lightRight = new LightSensor(SensorPort.S1);
		this.lightLeft = new LightSensor(SensorPort.S2);
		this.ultra = new UltrasonicSensor(SensorPort.S3);
	}
	
	public float getRange(){
		return ultra.getRange();
	}
	
	public void move(int travelSpeed){
		pilot.setTravelSpeed(travelSpeed);
		pilot.forward();
	}
}