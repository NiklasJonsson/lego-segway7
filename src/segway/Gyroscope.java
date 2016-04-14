package segway;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;

public class Gyroscope {

	private HiTechnicGyro gyro;
	private float[] data;
	
	public Gyroscope(){
		gyro=new HiTechnicGyro(SensorPort.S3);
		data=new float[gyro.sampleSize()];
	}
	
	public double[] read(){
		gyro.fetchSample(data, 0);
		double[] ret=new double[data.length];
		for(int i=0;i<data.length;i++)
			ret[i]=data[i];
		return ret;
	}
}
