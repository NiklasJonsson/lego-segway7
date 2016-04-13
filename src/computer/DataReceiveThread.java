package computer;

import java.io.IOException;

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
		while(!done) {
			Signals signals;
			try {
				signals = con.getSignals();
				System.out.println("Got signals: " + signals);
				mon.newData(signals);
			} catch (ClassNotFoundException | IOException e) {
				done = true;
				e.printStackTrace();
			}
			   
		}
		System.out.println("DataReceiveThread exiting...");
	}
}

