package Segway;

public class RegulatorThread extends Thread {

	private final double[][] a = new double[2][2];
	private final double[] b = new double[2];
	private final double[] c = new double[2];

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
			// get signals

			u = lr * r + l1 * x1 + l2 * x2 - v;
			e = y - x2;
			double x1new = a[0][0] * x1 + a[0][1] * x2 + b[0] * u + k1 * e;
			x2 = a[1][0] * x1 + a[1][1] * x2 + b[1] * u + k2 * e;
			x1 = x1new;
			v = v + kv * e;

		}
	}
}
