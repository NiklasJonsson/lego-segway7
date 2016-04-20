package segway;

import utility.Parameters;
import utility.Signals;

public class ObserverRegulator implements Regulator {
	private static final double[][] a = new double[][] { { 1.2287, 0.0538 }, { 9.4827, 1.2287 } };
	private static final double[] b = new double[] { 0.0583, 2.4191 };

	private RegulatorMonitor rm;
	private Parameters p;

	private double x1 = 0;
	private double x2 = 0;

	private double e = 0;
	private double v = 0;

	public ObserverRegulator(RegulatorMonitor rm) {
		this.rm = rm;
	}

	@Override
	public double calculateSignal(double[] accel, double angularVelocity) {
		p = rm.getParameters();
		return p.Lr * p.r + p.L1 * x1 + p.L2 * x2 - v;
	}

	@Override
	public void updateState(double u, double y) {
		e = y - x2;
		double old_x1 = x1;
		x1 = a[0][0] * x1 + a[0][1] * x2 + b[0] * u + p.K1 * e;
		x2 = a[1][0] * old_x1 + a[1][1] * x2 + b[1] * u + p.K2 * e;

		v = v + p.Kv * e;

		rm.setSignals(new Signals(u, y, x1, x2, p.L1, p.L2, p.Lr, p.r));
	}

}
