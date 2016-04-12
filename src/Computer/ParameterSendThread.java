package Computer;

import java.io.IOException;

public class ParameterSendThread extends Thread {
	
	private SegwayConnection con;
	private ParameterMonitor paraMon;

	public ParameterSendThread(SegwayConnection con, ParameterMonitor paraMon) {
		this.paraMon = paraMon;
		this.con = con;
	}
	
	@Override
	public void run() {
		System.out.println("ParameterSendThread started...");
		boolean done = false;
		while(!done) {
			try {
				Parameters params = paraMon.getParameters();
				System.out.println("Sending parameters: " + params);
				con.sendParameters(params);
			} catch (InterruptedException | IOException e) {
				done = false;
				e.printStackTrace();
			}
		}
		System.out.println("ParameterSendThread exiting...");
	}	
}
