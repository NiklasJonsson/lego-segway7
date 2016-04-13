package segway;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import utility.Signals;

public class DataSendThread extends Thread {
	private ComputerConnection con;
	private RegulatorMonitor mon;

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
				con.sendSignals(signals);
				Thread.sleep(1000);
			} catch (IOException | InterruptedException e) {
				done = true;
				e.printStackTrace();
			}
		}

	}

}
