package Segway;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Computer.Signals;

public class DataSendThread extends Thread{
	private int port;

	public DataSendThread(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
		   ServerSocket serverSocket = new ServerSocket(port);
		   Socket socket = serverSocket.accept();
		   ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		   boolean done = false;
		   System.out.println("Connection accepted");
		   while(!done) {
			   //TODO Fetch signals from RegulatorMonitor
			   Signals signals = new Signals(1, 1,1 ,1 ,1, 1, 1);
			   out.writeObject(signals);
			   Thread.sleep(250);
		   }
		   socket.close();
		   serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
