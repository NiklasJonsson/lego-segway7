package segway;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;

public class Gyroscope {

	private HiTechnicGyro gyro;
	private float[] data;
	public double offSet = -7.11;
	
	public Gyroscope() {
		gyro = new HiTechnicGyro(SensorPort.S3);
		data = new float[gyro.sampleSize()];
	}
	
	/**
	 * 
	 * @return A double array with one value. Negative value for angular tilt backwards
	 * positive for angular tilt forwards. Stationary disturbance of +-7 deg/s
	 */
	public double[] read(){
		gyro.fetchSample(data, 0);
		double[] ret = new double[data.length];
		for (int i = 0; i < data.length; i++)
			ret[i] = data[i] - offSet;
		return ret;
	}
}
