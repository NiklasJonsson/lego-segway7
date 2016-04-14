package segway;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Motor {

	private EV3LargeRegulatedMotor left;
	private EV3LargeRegulatedMotor right;

	public Motor() {
		left = new EV3LargeRegulatedMotor(MotorPort.D);
		right = new EV3LargeRegulatedMotor(MotorPort.A);
	}

	public void move(double acc) {

	}
}
