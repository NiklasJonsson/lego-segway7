package segway;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicAccelerometer;

public class Accelerometer {
	
	private HiTechnicAccelerometer device;
	
	public Accelerometer() {
		device = new HiTechnicAccelerometer(SensorPort.S2);
	}
	/**
	 * 
	 * @return ret[0] is upwards/downwards acc, ret[1] is forward/backward acc and ret[2] side/side acc.
	 */
	public double[] read() {
		float[] data = new float[device.sampleSize()];
		device.fetchSample(data, 0);
		double[] ret = new double[device.sampleSize()];
		for(int i = 0; i < data.length; ++i) {
			ret[i] = data[i];
		}
		return ret;
	}
}
