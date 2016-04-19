package segway;

import java.io.IOException;
import utility.Signals;

public class DataSendThread extends Thread {
	private ComputerConnection con;
	private RegulatorMonitor mon;
	private static long sleepPeriod = 500;

	public DataSendThread(ComputerConnection con, RegulatorMonitor mon) {
		this.mon = mon;
		this.con = con;
	}

	@Override
	public void run() {
		boolean done = false;
		while (!done) {
			try {
				Signals signals = mon.getSignals();
				con.send(signals);
				Thread.sleep(sleepPeriod);

			} catch (IOException | InterruptedException e) {
				done = true;
				try {
					con.send(e);
				} catch (IOException e1) {
				}
				e.printStackTrace();
			} catch (Exception e) {
				try {
					con.send(e);
				} catch (IOException e1) {
				}
			}
		}
	}
}
