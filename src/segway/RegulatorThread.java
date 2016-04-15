package segway;

import utility.Parameters;
import utility.Signals;

public class RegulatorThread extends Thread {

	private final double[][] a;
	private final double[] b;
	private RegulatorMonitor rm;
	private Motors m;
	private Accelerometer acc;
	private Gyroscope gyro;
	
	
	public RegulatorThread(RegulatorMonitor rm){
		this.rm=rm;
		m = new Motors();
		acc = new Accelerometer();
		gyro = new Gyroscope();
		
		a=new double[][] {{0, 1}, {47.6757, 0}};
		b=new double[] {0, 12.1622};
	}

	public void run() {
		double y = 0;
		double u = 0;
		double x1 = 0;
		double x2 = 0;

		double e = 0;
		double v = 0;
		int i = 1;
		while (!Thread.interrupted()) {
			double[] accData = acc.read();
			double accel = accData[1];
			double[] velData = gyro.read();
			
			SegwayMain.printToScreen("0: " + accData[0], "1: " + accData[1], "2: " + accData[2], "Gyro: " + velData[0]);
			 // TODO add more variables in signals? (v, r...)
			
			Parameters p = rm.getParameters();
					
			u = p.Lr * p.r + p.L1 * x1 +p.L2 * x2 - v;
			
			m.sendSignal(u);
			
			e = y - x2;
			e=0;
			double old_x1 = x1;
			x1 = a[0][0] * x1 + a[0][1] * x2 + b[0] * u + p.K1 * e;
			x2 = a[1][0] * old_x1 + a[1][1] * x2 + b[1] * u + p.K2 * e;

			v = v + p.Kv * e;

			rm.setSignals(new Signals(u, y, x1, x2, p.L1, p.L2, p.Lr));
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
