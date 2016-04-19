package segway;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

public class Motor extends UnregulatedMotor {

	public Motor(Port portId) {
		super(portId);
	}

	public void sendSignal(double u) {
		if (u < 0) {
			updateState(BasicMotorPort.BACKWARD);
			u = -u;
		} else {
			updateState(BasicMotorPort.FORWARD);
		}
		u = u >= 80  ? 80 : u;
		setPower((int) Math.round(u));
	}
}
