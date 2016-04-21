package segway;

import lejos.hardware.port.MotorPort;

public class Motors {
	
	private Motor left;
	private Motor right;

	public Motors() {
		left = new Motor(MotorPort.D);
		right = new Motor(MotorPort.A);
	}

	public void sendSignal(int u) {
		left.sendSignal(u);
		right.sendSignal(u);
	}
	
	public static int limit(double u) {
		if (u > 99) {
			u = 99;
		} else if (u < -99){
			u = -99;
		}
		return (int) Math.round(u);
	}

}
