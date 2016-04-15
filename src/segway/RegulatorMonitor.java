package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorMonitor {
	private Signals signals;
	
	public Signals getSignals() {
		return new Signals(1, 1, 1, 1, 2, 2,2);
	}

	public void newParameters(Parameters params) {
		
	}

}
