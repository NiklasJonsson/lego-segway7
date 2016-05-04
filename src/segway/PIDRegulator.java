package segway;

import utility.Parameters;
import utility.Signals;

/**
 * Not actually used
 *
 */
public class PIDRegulator implements Regulator {
	private static final double[][] a = new double[][] { { 1.2287, 0.0538 }, { 9.4827, 1.2287 } };
	private static final double[] b = new double[] { 0.0583, 2.4191 };
	
	private double K = 10;// Gain
	private double Ti = 0.5; // Integral weight parameter
    private double Tr = 1;// Tracking weight
   	private double Td = 1.1; // Derivative weight
   	private double N = 6; // Low pass filter constant for derivate
    private double Beta = 1.0; // Set point weighting for P-part
    private double H = 0.05; // Sample time in seconds
    
    private boolean integratorOn = true;
	
	
	
    private double I  = 0; // Variable for storing integral sum
    private double e = 0; // Error from latest call to calculateOutput
    private double v = 0; // Desired control signal, used for tracking
    private double yOld = 0; // Old y value
    private double ad = Td/(Td+N*H); // D-part constant
    private double bd = K*ad*N; // D-part constant
    private double D; // Storing last D-computation

	private RegulatorMonitor rm;
	private Parameters p;
	
	private long startTime;


	public PIDRegulator(RegulatorMonitor rm) {
		startTime = System.currentTimeMillis();
		
		p = new Parameters(1, 1, 1, 1, 1, 1, false, 1);

		this.rm = rm;
	}

	@Override
	public double calculateSignal(double[] accel, double angularVelocity, double angle) {
		double ref = 0;
		double y = angle;
		e = ref - y;
		D = ad*D - bd*(y - yOld);
		v = K * (Beta*ref - y) + I + D; 
        yOld = y; // Could be moved to updateState() but not really needed (same #assign)
        return v; 
	}

	@Override
	public void updateState(double u, double y) {
        I = integratorOn ? I + (K*H/Ti)*e + (H/Tr)*(u - v): 0.0;
		rm.setSignals(new Signals((double)(System.currentTimeMillis() - startTime) / 1000.0, u, y, 0, 0, p));
	}

}
