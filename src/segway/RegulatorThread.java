package segway;

import lejos.hardware.lcd.LCD;
import utility.Signals;

public class RegulatorThread extends Thread {

	private final double[][] a = new double[2][2];
	private final double[] b = new double[2];
	private final double[] c = new double[2];
	private RegulatorMonitor rm;
	private Motors m;
	private Accelerometer acc;

	public RegulatorThread(RegulatorMonitor rm) {
		this.rm = rm;
		m = new Motors();
		acc = new Accelerometer();
	}

	public void run() {
		double y = 0;
		double u = 0;
		double x1 = 0;
		double x2 = 0;
		double r = 0;

		double lr = 0;
		double l1 = 0;
		double l2 = 0;

		double k1 = 0;
		double k2 = 0;
		double kv = 0;

		double e = 0;
		double v = 0;
		int i = 1;
		while (!Thread.interrupted()) {
			double[] data = acc.read();
			double accel = data[1];
			SegwayMain.printToScreen("0: " + data[0], "1: " + data[1], "2: " + data[2]);
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
