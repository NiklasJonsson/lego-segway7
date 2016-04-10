package Computer;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import SimEnvironment.Plotter;
import SimEnvironment.VirtualProcess;

public class PlotterGUI extends VirtualProcess {
	private DataMonitor mon;

	public PlotterGUI(DataMonitor mon) {
		super(3, 2, 2);
		this.mon = mon;
	}

	public void createAndShow() {
		Plotter plotter = new Plotter(3, 100, 10, -10);
		getSource(0).setPlotter(plotter, 0);
		getSink(0).setPlotter(plotter, 1);
		getSink(1).setPlotter(plotter, 2);

		JFrame frame = new JFrame("");
		frame.getContentPane().setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(plotter.getPanel());
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		PlotterGUI p = new PlotterGUI(new DataMonitor());
		p.createAndShow();
	}

	@Override
	public double[] computeOutput(double[] state, double[] input) {
		// TODO Auto-generated method stub
		return new double[] { state[0], state[1] };
	}

	@Override
	public double[] updateState(double[] state, double[] input, double h) {
		// TODO Auto-generated method stub
		return new double[] { state[0], state[1], state[2] };
	}
}
