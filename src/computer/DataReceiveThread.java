package computer;

import java.io.IOException;
import java.lang.Throwable;
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
			try {
				Object o = con.getSignals();
				if(o instanceof Signals){
					mon.newData((Signals) o);	
				}else{
				Exception e = (Exception) o;
				e.printStackTrace();
				}	
			} catch (ClassNotFoundException | IOException e) {
				done = true;
				e.printStackTrace();
			}
			   
		}
		System.out.println("DataReceiveThread exiting...");
	}
}

