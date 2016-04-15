package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorMonitor {
	private Signals signals;
	private Parameters params;
	

	final static double def_lr = 0.1549;
	final static double def_l1 = 4.0749;
	final static double def_l2 = 0.2543;
	

	final static double def_k1 = 1.3704;
	final static double def_k2 = 74.2533;
	final static double def_kv = 60.9601;
	
	final static boolean def_integratorOn = false;
	
	final static double def_r = 0;
	
	public RegulatorMonitor() {
		params = new Parameters(def_k1, def_k2, def_l1, def_l2, def_lr, def_kv, def_integratorOn, def_r);
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
