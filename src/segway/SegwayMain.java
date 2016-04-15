package segway;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class SegwayMain {
	static int currentRow = 0;

	public static void main(String[] args) {
		printToScreen("SegwayMain...");
		
		RegulatorMonitor mon = new RegulatorMonitor();
		RegulatorThread regulator = new RegulatorThread(mon);
		regulator.start();
		
		int port = 1234;
		ComputerConnection con = new ComputerConnection(port);
		try {
			con.connect();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		DataSendThread sender = new DataSendThread(con, mon);
		sender.start();
		ParameterReceiverThread receiver = new ParameterReceiverThread(mon, con);
		receiver.start();
		
	}

	public static void printToScreen(String s, String s2, String s3, String s4) { 		
		//final int width = 18;
		LCD.drawString(s, 0, 0);
		LCD.drawString(s2, 0, 1);
		LCD.drawString(s3, 0, 2);
		LCD.drawString(s4, 0, 3);

	}
	public static void printToScreen(String s) {
		//final int width = 18;
		int rowCount = 6;
		LCD.drawString(s, 0, currentRow);
		if (currentRow == rowCount) {
			LCD.clear(0);
			currentRow = 0;
		} else {
			currentRow++;
		}
		Delay.msDelay(500);
	}
	
	
}
