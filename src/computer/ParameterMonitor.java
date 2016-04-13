package computer;

import utility.Parameters;

public class ParameterMonitor {
	private Parameters params;
	private boolean newParams = false;
	
	public synchronized Parameters getParameters() throws InterruptedException {
		while(!newParams) {
			wait();
		}
		newParams = false;
		return params;
	}
	
	public synchronized void newParameters(Parameters p) throws CloneNotSupportedException {
		params = (Parameters) p.clone();
		newParams = true;
		notifyAll();
	}
}
