package computer;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import SimEnvironment.Plotter;
import SimEnvironment.VirtualProcess;
import se.lth.control.*;
import se.lth.control.plot.PlotterPanel;

public class PlotterGUI {
	
	// Signals: u, y, r
	private static final int NBR_OF_SIGNALS = 5;
	private static final int PLOTTER_PRIORITY = Thread.NORM_PRIORITY; // TODO Maybe change this?
	
	private static final int Y_AXIS_RANGE = 60;
	private static final int Y_AXIS_BOTTOM = -30;
	private static final int Y_AXIS_DIV_TICKS = 2;
	private static final int Y_AXIS_DIV_GRID = 2;
	
	private static final int X_AXIS_RANGE = 10;
	private static final int X_AXIS_DIV_TICKS = 5;
	private static final int X_AXIS_DIV_GRID = 5;
	
	private static final int PLOTTER_UPDATE_FREQ = 10;
	
	private JFrame mainFrame;
	private DataMonitor mon;

	public PlotterGUI(DataMonitor mon) {
		this.mon = mon;
	}

	public void createAndShow() {
		//PlotterPanel ctrl = new PlotterPanel(NBR_OF_PARAMETERS, PLOTTER_PRIORITY);
		PlotterPanel signalsPanel = new PlotterPanel(NBR_OF_SIGNALS, PLOTTER_PRIORITY);
		
		signalsPanel.setYAxis(Y_AXIS_RANGE, Y_AXIS_BOTTOM, Y_AXIS_DIV_TICKS, Y_AXIS_DIV_GRID);
		signalsPanel.setXAxis(X_AXIS_RANGE, X_AXIS_DIV_TICKS, X_AXIS_DIV_GRID);
		signalsPanel.setUpdateFreq(PLOTTER_UPDATE_FREQ);
		JFrame frame = new JFrame("Segway Control");
		frame.getContentPane().add(signalsPanel);
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
