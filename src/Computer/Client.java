package Computer;

import javax.swing.SwingUtilities;

public class Client {
	
	public static void main(String[] args){
		DataMonitor mon = new DataMonitor();
		final PlotterGUI plotter = new PlotterGUI(mon);
		DataReceiveThread receiver = new DataReceiveThread("localhost", 1234, mon);
		receiver.start();
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	plotter.createAndShow();
		    }
		});
		
	}
}
