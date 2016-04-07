package Computer;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PlotterGUI {
	private DataMonitor mon;

	public PlotterGUI(DataMonitor mon) {
		this.mon = mon;
	}
	
	public void createAndShow(){
		JFrame mainFrame = new JFrame("Signals");
		//JPanel contentPane = new JPanel();
		JTextField text = new JTextField("test");
		mainFrame.add(text);
		mainFrame.setSize(500, 500);
		mainFrame.setVisible(true);
	}
}
