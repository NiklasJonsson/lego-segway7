package segway;

import java.io.IOException;

import utility.Parameters;

public class ParameterReceiverThread extends Thread{
	private RegulatorMonitor mon;
	private ComputerConnection con;

	public ParameterReceiverThread(RegulatorMonitor mon, ComputerConnection con) {
		this.con = con;
		this.mon = mon;
	}



	@Override
	public void run() {
		boolean done = false;
		
		while (!done) {
			try {
				Parameters params = con.getParameters();
				mon.newParameters(params);
			} catch (ClassNotFoundException | IOException e) {
				done = true;
				try {
					con.send(e);
				} catch (IOException e1) {
				}
				e.printStackTrace();
			} 
			
		}
	}

}
