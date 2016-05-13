package computer;

import java.io.IOException;

import se.lth.control.plot.PlotterPanel;
import utility.Signals;

public class DataReceiveThread extends Thread {
	private SegwayConnection con;
	private DataMonitor mon;

	public DataReceiveThread(SegwayConnection con, DataMonitor mon) {
		this.con = con;
		this.mon = mon;
	}

	public void run() {
		System.out.println("DataReceiveThread started...");
		boolean done = false;
		while (!done) {
			try {
				Object o = con.getSignals();
				if (o instanceof Signals) {
					Signals s = (Signals) o;
					System.out.println(s.toString());
					mon.newData((Signals) o);
				} else {
					Exception e = (Exception) o;
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				done = true;
				e.printStackTrace();
			} catch (IOException e) {
				try {
					con.connect();
				} catch (IOException e2) {
				//	System.out.println("Disconnected");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}

			}

		}
		System.out.println("DataReceiveThread exiting...");
	}
}
