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

	// Signals: u, y, r
	private static final int NBR_OF_SIGNALS = 5;
	 // TODO Another priority here?
	private static final int PLOTTER_PRIORITY = Thread.NORM_PRIORITY;

	private static final int Y_AXIS_RANGE = 60;
	private static final int Y_AXIS_BOTTOM = -30;
	private static final int Y_AXIS_DIV_TICKS = 2;
	private static final int Y_AXIS_DIV_GRID = 2;

	private static final int X_AXIS_RANGE = 15;
	private static final int X_AXIS_DIV_TICKS = 5;
	private static final int X_AXIS_DIV_GRID = 5;

	private static final int PLOTTER_UPDATE_FREQ = 10;

	private DataMonitor mon;
	private PlotterPanel signalsPanel;

	public PlotterGUI(DataMonitor mon) {
		this.mon = mon;
	}

	public void createAndShow() {
		// PlotterPanel ctrl = new PlotterPanel(NBR_OF_PARAMETERS,
		// PLOTTER_PRIORITY);
		signalsPanel = new PlotterPanel(NBR_OF_SIGNALS, PLOTTER_PRIORITY);

		signalsPanel.setYAxis(Y_AXIS_RANGE, Y_AXIS_BOTTOM, Y_AXIS_DIV_TICKS, Y_AXIS_DIV_GRID);
		signalsPanel.setXAxis(X_AXIS_RANGE, X_AXIS_DIV_TICKS, X_AXIS_DIV_GRID);
		signalsPanel.setUpdateFreq(PLOTTER_UPDATE_FREQ);

		JFrame frame = new JFrame("Segway Control");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(signalsPanel);

		frame.pack();
		frame.setVisible(true);
		// Second Class parameter (Integer) is not used
		signalsPanel.start();
		SignalFetcher fetcher = new SignalFetcher();
		fetcher.execute();
	}

	public static void main(String[] args) {
		PlotterGUI p = new PlotterGUI(new DataMonitor());
		p.createAndShow();
	}

	private class SignalFetcher extends SwingWorker<Integer, Signals> {
		private long startTime;

		/**
		 * Called by a regular thread, not EDT Should not exit since we do not
		 * want to stop fetching signals
		 */
		@Override
		public Integer doInBackground() throws Exception {
			startTime = System.currentTimeMillis();
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
					signalsPanel.putData(s.sampleTime, s.y, s.parameters.r, s.u,
							s.y - s.parameters.r);
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
}
