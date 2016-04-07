package Segway;

public class Server {

	public static void main(String[] args) {
		DataSendThread sender = new DataSendThread("localhost", 1234);
		sender.start();
	}
}
