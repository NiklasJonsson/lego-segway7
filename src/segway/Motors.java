package segway;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.BasicMotorPort;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

public class Motors {
	
	private Motor left;
	private Motor right;

	public Motors() {
		left = new Motor(MotorPort.D);
		right = new Motor(MotorPort.A);
	}

	public void sendSignal(double u) {
		left.setPower((int) Math.round(u));
		right.setPower((int) Math.round(u));
	}

}
