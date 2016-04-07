package Computer;

import java.io.ObjectInputStream;
import java.net.Socket;

public class DataReceiveThread extends Thread {
	private String hostName;
	private int port;
	private DataMonitor mon;

	public DataReceiveThread(String hostName, int port, DataMonitor mon) {
		this.hostName = hostName;
		this.port = port;
		this.mon = mon;
	}
	
	public void run() {
		try {
		   Socket socket = new Socket(hostName, port);
		   ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		   boolean done = false;
		   while(!done) {
			   Signals signals = (Signals) in.readObject();
			   System.out.println(signals);
			   mon.newData(signals);
		   }
		   socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

