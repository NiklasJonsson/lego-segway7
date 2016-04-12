package Computer;

import javax.swing.SwingUtilities;


public class ComputerMain {
	
	public static void main(String[] args){
		if (args == null || args.length != 2) {
			System.err.println("Incorrect usage. First parameter is host ip and second is host port");
		}
		DataMonitor dataMon = new DataMonitor();
		ParameterMonitor paraMon = new ParameterMonitor(); 
		SegwayConnection con = new SegwayConnection(args[0], Integer.parseInt(args[1]));
		DataReceiveThread receiver = new DataReceiveThread(con, dataMon);
		receiver.start();
		ParameterSendThread sender = new ParameterSendThread(con, paraMon);
		sender.start();
		final PlotterGUI plotter = new PlotterGUI(dataMon);
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	plotter.createAndShow();
		    }
		});
		
	}
}
