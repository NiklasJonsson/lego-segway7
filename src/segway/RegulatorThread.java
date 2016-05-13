package segway;

import java.io.IOException;

public class RegulatorThread extends Thread {
	public final static long h = 30;

	private Motors m;
	private Accelerometer acc;
	private Gyroscope gyro;
	private Regulator regulator;

	public RegulatorThread(Regulator regulator) {
		this.regulator = regulator;
		m = new Motors();
		acc = new Accelerometer();
		gyro = new Gyroscope();
	}

	public void run() {
		double y = 0; //angular position
		double u = 0;

		while (!Thread.interrupted()) {
			try {
				long t1 = System.currentTimeMillis();
				double[] accData = acc.read(); //reads absolute acceleration in x, y, z
				double[] velData = gyro.read(); //reads angular velocity

				// TODO: We should be able to calculate the angle using the accelerometer
				// with y=acc[1]*90/9.82 or something like that
				
				//might work to cancel out drift
/*				double offset=0;
				if(Math.abs(accData[1])<0.3){ //or we might be able to use accData[0]-9.82
					offset=velData[0];
					y=0;
				}
				y = y + (velData[0]-offset) * ((double) h) / 1000.0;
*/
/* This is pure integration and does not use accel data to fix drfit
 *  
 *				y = y + velData[0] * ((double) h) / 1000.0; 
*/
/* This is the balance filter from the blog
*/ 				y = (0.95) * (y + velData[0] * ((double) h) / 1000.0) + (0.05) * accData[1];

				
				u = Motors.limit(regulator.calculateSignal(accData, velData[0], y));
				m.sendSignal((int) u);
				
				regulator.updateState(u, y);
				//SegwayMain.printToScreen("0: " + accData[0], "1: " + accData[1], "2: " + accData[2], "Gyro: " + velData[0], "u: " + u);
				long t2 = System.currentTimeMillis();
				long toSleep = h - (t2 - t1);
				if (toSleep > 0) {
					//SegwayMain.printToScreen(toSleep + "Positive sleep time");
					Thread.sleep(toSleep);
				} else {
					SegwayMain.printToScreen(toSleep + "Negative sleep time");
				}
			} catch (Exception e1) {
					e1.printStackTrace();
			}
		}

	}
}
