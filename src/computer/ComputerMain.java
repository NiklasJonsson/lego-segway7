package computer;

import java.io.IOException;

import javax.swing.SwingUtilities;

import utility.Signals;

public class ComputerMain {
	static final int DEF_PORT = 1234;
	static final String DEF_HOST = "10.0.1.1";

	public static void main(String[] args) {
		int port = DEF_PORT;
		String host = DEF_HOST;

		if (args == null || args.length != 2) {
			System.out.println("Using default parameters: " + DEF_HOST + " " + DEF_PORT);
		} else {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		DataMonitor dataMon = new DataMonitor();
		ParameterMonitor paraMon = new ParameterMonitor();
		
		SegwayConnection con = new SegwayConnection(host, port);
		boolean connected = false;
		Signals sig = null;
		while (!connected) {
			try {
				connected = con.connect();
				sig = (Signals) con.getSignals();
			} catch (IOException e) {
				System.out.println("Not connected");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e.printStackTrace();
					return;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		

		final PlotterGUI plotter = new PlotterGUI(dataMon);
		final ParameterGUI paramGUI = new ParameterGUI(paraMon, sig.parameters);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				plotter.createAndShow();
				paramGUI.createAndShow();
			}
		});

		DataReceiveThread receiver = new DataReceiveThread(con, dataMon);
		receiver.start();
		ParameterSendThread sender = new ParameterSendThread(con, paraMon);
		sender.start();
	}
}
