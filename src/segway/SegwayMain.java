package segway;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class SegwayMain {

	public static void main(String[] args) {
		printToScreen("SegwayMain...");
		int port = 1234;
		ComputerConnection con = new ComputerConnection(port);
		try {
			con.connect();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		RegulatorMonitor mon = new RegulatorMonitor();
		DataSendThread sender = new DataSendThread(con, mon);
		sender.start();
		ParameterReceiverThread receiver = new ParameterReceiverThread(mon, con);
		receiver.start();
	}

	public static void printToScreen(String s) {
		LCD.clear();
		LCD.drawString(s, 0, 0);
		Delay.msDelay(500);
	}
}
