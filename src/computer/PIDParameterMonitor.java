package computer;

import utility.PIDParameters;
import utility.Parameters;

public class PIDParameterMonitor {
	private PIDParameters params;
	private boolean newParams = false;
	
	public synchronized PIDParameters getParameters() throws InterruptedException {
		while(!newParams) {
			wait();
		}
		newParams = false;
		return params;
	}
	
	public synchronized void newParameters(PIDParameters p) throws CloneNotSupportedException {
		params = (PIDParameters) p.clone();
		newParams = true;
		notifyAll();
	}
}
