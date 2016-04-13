package segway;

import utility.Signals;

public class RegulatorThread extends Thread {

	private final double[][] a = new double[2][2];
	private final double[] b = new double[2];
	private final double[] c = new double[2];
	RegulatorMonitor rm;
	
	public RegulatorThread(RegulatorMonitor rm){
		this.rm=rm;
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
		while (!Thread.interrupted()) {
			 // TODO add more variables in signals? (v, r...)
			Signals s = rm.getSignals();

			u = lr * r + s.l1 * s.x1 + s.l2 * s.x2 - v;
			e = y - x2;
			x1 = a[0][0] * s.x1 + a[0][1] * s.x2 + b[0] * u + k1 * e;
			x2 = a[1][0] * s.x1 + a[1][1] * s.x2 + b[1] * u + k2 * e;

			v = v + kv * e;

			// TODO write signals
		}
	}
}
