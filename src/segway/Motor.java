package segway;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

public class Motor extends UnregulatedMotor {

	public Motor(Port portId) {
		super(portId);
	}

	public void sendSignal(int percent) {
//		if (percent < 0) {
//			updateState(BasicMotorPort.BACKWARD);
//		} else {
//			updateState(BasicMotorPort.FORWARD);
//		}
		setPower(percent);
	}
}
