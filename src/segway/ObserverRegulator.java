package segway;

import utility.ObserverParameters;
import utility.Parameters;
import utility.Signals;

public class ObserverRegulator extends RegulatorMonitor {
	private static final double[][] a = new double[][] { { 1.1422, 0.0523 }, { 5.8178, 1.1422 } };
	private static final double[] b = new double[] { 0.0238, 0.9732 };

	private double x1 = 0; //angular position
	private double x2 = 0; //angular velocity

	private double e = 0;
	private double v = 0;
	
	private long startTime;

	public ObserverRegulator() {
		super(new ObserverParameters());
		startTime = System.currentTimeMillis();
	}

	@Override
	public synchronized double calculateSignal(double[] accel, double angularVelocity, double angle) {
		ObserverParameters p = (ObserverParameters) params;
		return p.Lr * p.R - p.L1 * x1 - p.L2 * x2 - v;
	}

	@Override
	public synchronized void updateState(double u, double y) {
		ObserverParameters p = (ObserverParameters) params;
		e = y - x1;
		double old_x1 = x1;
		x1 = a[0][0] * x1 + a[0][1] * x2 + b[0] * (u+v) + p.K1 * e;
		x2 = a[1][0] * old_x1 + a[1][1] * x2 + b[1] * (u+v) + p.K2 * e;

		v = v + p.Kv * e;

		setSignals(new Signals((double)(System.currentTimeMillis() - startTime) / 1000.0, u, y, p));
	}

}
