package segway;

import java.io.IOException;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import utility.PIDParameters;

public class SegwayMain {
	static final int START_ROW = 6;
	static int currentRow = START_ROW;
	
	public static void main(String[] args) throws IOException {
		printToScreen("SegwayMain...");
		int port = 1234;
		PIDRegulator reg = new PIDRegulator();
		ComputerConnection con = new ComputerConnection(port);
		RegulatorThread regulator = new RegulatorThread(con, reg);
		
		try{
			regulator.start();
			con.connect();
			regulator.addDebugConnection(con);
			DataSendThread sender = new DataSendThread(con, reg);
			sender.start();
			ParameterReceiverThread receiver = new ParameterReceiverThread(reg, con);
			receiver.start();
		}catch (Exception e) {
			con.send(e);
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
		int rowCount = 5;
		LCD.drawString(s, 0, currentRow);
		if (currentRow == rowCount) {
			LCD.clear(START_ROW);
			currentRow = START_ROW;
		} else {
			//currentRow++;
		}
		Delay.msDelay(500);
	}
}
