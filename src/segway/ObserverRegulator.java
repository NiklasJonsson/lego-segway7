package segway;

import utility.Parameters;
import utility.Signals;

public class ObserverRegulator implements Regulator {
	private static final double[][] a = new double[][] { { 1.1422, 0.0523 }, { 5.8178, 1.1422 } };
	private static final double[] b = new double[] { 0.0238, 0.9732 };

	private RegulatorMonitor rm;
	private Parameters p;

	private double x1 = 0; //angular position
	private double x2 = 0; //angular velocity

	private double e = 0;
	private double v = 0;
	
	private long startTime;

	public ObserverRegulator(RegulatorMonitor rm) {
		startTime = System.currentTimeMillis();
		this.rm = rm;
	}

	@Override
	public double calculateSignal(double[] accel, double angularVelocity) {
		p = rm.getParameters();
		return p.Lr * p.r - p.L1 * x1 - p.L2 * x2 - v;
	}

	@Override
	public void updateState(double u, double y) {
		e = y - x1;
		double old_x1 = x1;
		x1 = a[0][0] * x1 + a[0][1] * x2 + b[0] * u + p.K1 * e;
		x2 = a[1][0] * old_x1 + a[1][1] * x2 + b[1] * u + p.K2 * e;

		v = v + p.Kv * e;
		rm.setSignals(new Signals((double)(System.currentTimeMillis() - startTime) / 1000.0, u, y, x1, x2, p));
	}

}
