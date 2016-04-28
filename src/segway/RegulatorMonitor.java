package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorMonitor {
	private Signals signals;
	private Parameters params;

	final static double DEFAULT_LR = 1.0696;
	final static double DEFAULT_L1 = 7.0476;
	final static double DEFAULT_L2 = 0.5765;

	final static double DEFAULT_K1 = 0.8343;
	final static double DEFAULT_K2 = 9.7273;// 74.2533
	final static double DEFAULT_KV = 0.1577;// 60.9601

	final static boolean DEFAULT_INTEGRATOR_ON = false;

	final static double DEFAULT_R = 0;

	public RegulatorMonitor() {
		params = new Parameters(DEFAULT_K1, DEFAULT_K2, DEFAULT_L1, DEFAULT_L2, DEFAULT_LR, DEFAULT_KV,
				DEFAULT_INTEGRATOR_ON, DEFAULT_R);
	}

	public synchronized Signals getSignals() {
		return (Signals) signals.clone();
	}

	public synchronized void newParameters(Parameters params) {
		this.params = (Parameters) params.clone();
	}

	public synchronized Parameters getParameters() {
		return (Parameters) params.clone();
	}

	public synchronized void setSignals(Signals s) {
		signals = (Signals) s.clone();
	}

}
