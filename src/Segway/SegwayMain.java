package Segway;

public class SegwayMain {

	public static void main(String[] args) {
		DataSendThread sender = new DataSendThread(1234);
		sender.start();
	}
}
