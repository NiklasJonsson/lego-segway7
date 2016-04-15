package segway;

import lejos.hardware.lcd.LCD;
import utility.Signals;

public class RegulatorThread extends Thread {

	private final double[][] a;
	private final double[] b;
	private final double[] c;
	private RegulatorMonitor rm;
	private Motors m;
	private Accelerometer acc;
	private Gyroscope gyro;
	
	
	public RegulatorThread(RegulatorMonitor rm){
		this.rm=rm;
		m = new Motors();
		acc = new Accelerometer();
		gyro = new Gyroscope();
		
		a=new double[][] {{0, 1}, {47.6757, 0}};
		b=new double[] {0, 12.1622};
	}

	public void run() {
		double y = 0;
		double u = 0;
		double x1 = 0;
		double x2 = 0;
		double r = 0;

		double lr = 0.1549;
		double l1 = 4.0749;
		double l2 = 0.2543;

		double k1 = 1.3704;
		double k2 = 74.2533;
		double kv = 60.9601;

		double e = 0;
		double v = 0;
		int i = 1;
		while (!Thread.interrupted()) {
			double[] accData = acc.read();
			double accel = accData[1];
			double[] velData = gyro.read();
			
			SegwayMain.printToScreen("0: " + accData[0], "1: " + accData[1], "2: " + accData[2], "Gyro: " + velData[0]);
			 // TODO add more variables in signals? (v, r...)
			Signals s = rm.getSignals();

			u = lr * r + s.l1 * s.x1 + s.l2 * s.x2 - v;
			e = y - x2;
			e=0;
			x1 = a[0][0] * s.x1 + a[0][1] * s.x2 + b[0] * u + k1 * e;
			x2 = a[1][0] * s.x1 + a[1][1] * s.x2 + b[1] * u + k2 * e;

			v = v + kv * e;

			// TODO write signals
			m.sendSignal(u);
			i *= -1;

			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
