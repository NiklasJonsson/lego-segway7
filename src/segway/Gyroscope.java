package segway;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;

public class Gyroscope {

	private HiTechnicGyro gyro;
	
	public Gyroscope(){
		gyro=new HiTechnicGyro(SensorPort.S3);
	}
	
	public double[] read(){
		float[] data=new float[gyro.sampleSize()];
		gyro.fetchSample(data, 0);
		double[] ret=new double[data.length];
		for(int i=0;i<data.length;i++)
			ret[i]=data[i];
		return ret;
	}
}
