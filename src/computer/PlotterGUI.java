package computer;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import se.lth.control.plot.PlotterPanel;
import utility.Signals;

public class PlotterGUI {

	private static final int MAX_NBR_OF_CHANNELS = 2;
	private static final int PLOTTER_PRIORITY = 8;

	private static final int Y_AXIS_RANGE = 200;
	private static final int Y_AXIS_BOTTOM = -100;
	private static final int Y_AXIS_DIV_TICKS = 2;
	private static final int Y_AXIS_DIV_GRID = 2;

	private static final int X_AXIS_RANGE = 15;
	private static final int X_AXIS_DIV_TICKS = 5;
	private static final int X_AXIS_DIV_GRID = 5;

	private static final int PLOTTER_UPDATE_FREQ = 1;

	private DataMonitor mon;
	private PlotterPanel signalsPanel;

	public PlotterGUI(DataMonitor mon) {
		this.mon = mon;
	}

	public void createAndShow() {
		// PlotterPanel ctrl = new PlotterPanel(NBR_OF_PARAMETERS,
		// PLOTTER_PRIORITY);
		signalsPanel = new PlotterPanel(MAX_NBR_OF_CHANNELS, PLOTTER_PRIORITY);

		signalsPanel.setYAxis(Y_AXIS_RANGE, Y_AXIS_BOTTOM, Y_AXIS_DIV_TICKS, Y_AXIS_DIV_GRID);
		signalsPanel.setXAxis(X_AXIS_RANGE, X_AXIS_DIV_TICKS, X_AXIS_DIV_GRID);
		signalsPanel.setUpdateFreq(PLOTTER_UPDATE_FREQ);

		JFrame frame = new JFrame("Segway Control");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(signalsPanel);

		
		signalsPanel.start();
		SignalFetcher fetcher = new SignalFetcher();
		fetcher.execute();
		
		frame.pack();
		frame.setVisible(true);

		
	}

	public static void main(String[] args) {
		PlotterGUI p = new PlotterGUI(new DataMonitor());
		p.createAndShow();
	}
	// TODO Switch this to a regular thread?
	private class SignalFetcher extends SwingWorker<Integer, Signals> {
		
		/**
		 * Called by a regular thread, not EDT Should not exit since we do not
		 * want to stop fetching signals
		 */
		@Override
		public Integer doInBackground() throws Exception {
			while (true) {
				publish(mon.readData());
			}
		}

		/**
		 * Called by the EDT to process all the elements published in
		 * doInBackground()
		 */
		@Override
		public void process(List<Signals> signalPackets) {
			if (signalsPanel != null) {
				for (Signals s : signalPackets) {
					System.out.println(s.toString());
					signalsPanel.putData(s.sampleTime, s.y, s.u);
				}
			}
		};

		/**
		 * Not used, thread will never exit
		 */
		@Override
		public void done() {
		}
	}
	public PlotterPanel getPlotter() {
		return signalsPanel;
	}
}
