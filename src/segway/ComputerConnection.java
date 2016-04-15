package segway;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import lejos.hardware.lcd.LCD;
import utility.*;

/**
 * Represents a connection to a EV3 lego
 * 
 * @author niklas
 *
 */
public class ComputerConnection {
	private Socket socket;
	private ServerSocket serverSocket;
	private boolean connected = false;
	private int port;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ComputerConnection(int port) {
		this.port = port;
	}

	public boolean connect() throws UnknownHostException, IOException {
		serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		SegwayMain.printToScreen("Accepted Connection");
		connected = true;
		return connected;
	}

	public boolean isConnected() {
		return connected;
	}

	public void close() throws IOException {
		socket.close();
		serverSocket.close();
	}

	public void sendSignals(Signals signals) throws IOException {
		out.writeObject(signals);
	}

	public Parameters getParameters() throws ClassNotFoundException, IOException {
		return (Parameters) in.readObject();
	}
	public void sendErrors(Exception e) throws IOException{
		out.writeObject(e);
	}
}
