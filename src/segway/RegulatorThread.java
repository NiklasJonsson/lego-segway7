package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorThread extends Thread {
	private final static long h = 50;

	private Motors m;
	private Accelerometer acc;
	private Gyroscope gyro;
	private Regulator regulator;
	
	
	public RegulatorThread(RegulatorMonitor rm){
		this.regulator = new ObserverRegulator(rm);
		m = new Motors();
		acc = new Accelerometer();
		gyro = new Gyroscope();
	}

	public void run() {
		double y = 0;
		double u = 0;

		while (!Thread.interrupted()) {
			double[] accData = acc.read();
			double[] velData = gyro.read();
			
			u = regulator.calculateSignal(accData, velData[0]);
			m.sendSignal(u);
			
			y = y + velData[0] * ((double) h)/1000.0; // We have to integrate to get y
			regulator.updateState(u, y);
			SegwayMain.printToScreen("0: " + accData[0], "1: " + accData[1], "2: " + accData[2], "Gyro: " + velData[0], "u: " + u);
			
			try {
				Thread.sleep(h);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				Thread.currentThread().interrupt();
				break;
			}
		}
	}
}
