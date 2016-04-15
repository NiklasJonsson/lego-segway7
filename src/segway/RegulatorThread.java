package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorThread extends Thread {

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

			SegwayMain.printToScreen("0: " + accData[0], "1: " + accData[1], "2: " + accData[2], "Gyro: " + (velData[0] - gyro.offSet));
			
			u = regulator.calculateSignal(accData, velData[0]);
			m.sendSignal(u);
			regulator.updateState(u, y);
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();

				Thread.currentThread().interrupt();
				break;
			}
		}
	}
}
