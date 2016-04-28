package segway;

import java.io.IOException;

public class RegulatorThread extends Thread {
	private final static long h = 50;

	private Motors m;
	private Accelerometer acc;
	private Gyroscope gyro;
	private Regulator regulator;
	private ComputerConnection con;
	private boolean hasDebugConnection;

	public RegulatorThread(RegulatorMonitor rm, ComputerConnection con) {
		this.con = con;
		this.regulator = new ObserverRegulator(rm);
		m = new Motors();
		acc = new Accelerometer();
		gyro = new Gyroscope();
		hasDebugConnection = false;
	}

	public void run() {
		double y = 0; //angular position
		double u = 0;

		while (!Thread.interrupted()) {
			try {
				long t1 = System.currentTimeMillis();
				double[] accData = acc.read(); //reads absolute acceleration in x, y, z
				double[] velData = gyro.read(); //reads angular velocity

				u = Motors.limit(regulator.calculateSignal(accData, velData[0]));
				m.sendSignal((int) u);

				// TODO: We should be able to calculate the angle using the accelerometer
				// with y=acc[1]*90/9.82 or something like that
				
				//might work to cancel out drift
//				double offset=0;
//				if(Math.abs(accData[1])<0.1){ or we might be able to use accData[0]-9.82
//					offset=velData[0];
//					y=0;
//				}
//				y = y + (velData[0]-offset) * ((double) h) / 1000.0;
				
				y = y + velData[0] * ((double) h) / 1000.0; // We have to
															// integrate to get
															// y
				regulator.updateState(u, y);
				SegwayMain.printToScreen("0: " + accData[0], "1: " + accData[1], "2: " + accData[2], "Gyro: " + velData[0], "u: " + u);
				long t2 = System.currentTimeMillis();
				long toSleep = h - (t2 - t1);
				if (toSleep > 0) {
					Thread.sleep(toSleep);
				} else {
					SegwayMain.printToScreen("Negative sleep time");
				}
			} catch (Exception e1) {
				if (hasDebugConnection) {
					try {
						con.send(e1);
					} catch (IOException e) {
						// Do nothing
					}
					e1.printStackTrace();
					Thread.currentThread().interrupt();
					break;
				} else {
					e1.printStackTrace();
				}
			}
		}

	}

	public void addDebugConnection(ComputerConnection con2) {
		con = con2;
		hasDebugConnection = true;
	}
}
