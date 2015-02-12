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
		if(travelSpeed > 0)
		{
			pilot.setTravelSpeed(travelSpeed);
			pilot.forward();
		} else {
			pilot.setTravelSpeed(-travelSpeed);
			pilot.backward();
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
}