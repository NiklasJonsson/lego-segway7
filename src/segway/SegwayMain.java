package segway;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class SegwayMain {
	static int currentRow = 0;
	
	public static void main(String[] args) throws IOException {
		printToScreen("SegwayMain...");
		RegulatorMonitor mon = new RegulatorMonitor();
		RegulatorThread regulator = new RegulatorThread(mon);
		int port = 1234;
		ComputerConnection con = new ComputerConnection(port);
		
		try{
			regulator.start();	
			con.connect();
			DataSendThread sender = new DataSendThread(con, mon);
			sender.start();
			ParameterReceiverThread receiver = new ParameterReceiverThread(mon, con);
			receiver.start();
		}catch (Exception e) {
			con.sendErrors(e);
			return;
		}
	}

	public static void printToScreen(String s1, String s2, String s3, String s4, String s5) { 		
		//final int width = 18;
		LCD.drawString(s1, 0, 0);
		LCD.drawString(s2, 0, 1);
		LCD.drawString(s3, 0, 2);
		LCD.drawString(s4, 0, 3);
		LCD.drawString(s5, 0, 4);
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
