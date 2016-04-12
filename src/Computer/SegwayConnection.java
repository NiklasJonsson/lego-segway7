package Computer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Represents a connection to a EV3 lego
 * 
 * @author niklas
 *
 */
public class SegwayConnection {
	private Socket sock;
	private boolean connected = false;
	private String address;
	private int port;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public SegwayConnection(String address, int port ) { 
		this.address = address;
		this.port = port;
	}
	
	public boolean connect() throws UnknownHostException, IOException {
		sock = new Socket(address, port);
		out = new ObjectOutputStream(sock.getOutputStream());
		in = new ObjectInputStream(sock.getInputStream());
		return connected = sock.isConnected();	
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void close() throws IOException {
		sock.close();
	}

	public void sendParameters(Parameters parameters) throws IOException {
		out.writeObject(parameters);
	}
	 
	public Signals getSignals() throws IOException, ClassNotFoundException {
		return (Signals) in.readObject();
	}
}