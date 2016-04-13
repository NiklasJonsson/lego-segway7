package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorMonitor {

	public Signals getSignals() {
		// TODO return a clone of the internal parameters
		return new Signals(1, 1, 1, 1, 2, 2,2);
	}

	public void newParameters(Parameters params) {
		// TODO set internal parameters and notify waiting threads
		
	}

}
