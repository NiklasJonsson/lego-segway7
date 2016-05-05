package segway;

import utility.ObserverParameters;
import utility.Parameters;
import utility.Signals;

public abstract class RegulatorMonitor implements Regulator{
	private Signals signals;
	protected Parameters params;


	protected RegulatorMonitor(Parameters p) {
		params = (Parameters) p.clone();
	}

	public synchronized Signals getSignals() {
		return (Signals) signals.clone();
	}

	public synchronized void newParameters(Parameters params) {
		this.params = (Parameters) params.clone();
	}


	protected synchronized void setSignals(Signals s) {
		signals = (Signals) s.clone();
	}

	@Override
	public abstract double calculateSignal(double[] accel, double angularVelocity, double angle);

	@Override
	public abstract void updateState(double u, double y);

}
