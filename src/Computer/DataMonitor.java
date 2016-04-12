package Computer;

public class DataMonitor {
	private Signals signals;
	private boolean newData = false;
	
	public synchronized void newData(Signals newSignals) {		
		signals = (Signals) newSignals.clone();
		newData = true;
		notifyAll();
	}
	
	public synchronized Signals readData() throws InterruptedException {
		while(!newData) {
			wait();
		}
		newData = false;
		return (Signals) signals.clone();
	}
}
