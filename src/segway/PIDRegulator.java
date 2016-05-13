package segway;

import utility.PIDParameters;
import utility.Parameters;
import utility.Signals;

/**
 * Not actually used
 *
 */
public class PIDRegulator extends RegulatorMonitor {
	
    //private boolean integratorOn = true;
	
    private double I  = 0; // Variable for storing integral sum
    private double e = 0; // Error from latest call to calculateOutput
    private double v = 0; // Desired control signal, used for tracking
    private double yOld = 0; // Old y value
    private double ad; // D-part constant
    private double bd; // D-part constant
    private double D; // Storing last D-computation

	private long startTime;

	public PIDRegulator() {
		super(new PIDParameters());
		startTime = System.currentTimeMillis();
		recalculate();
	}

	private void recalculate() {
		PIDParameters p = (PIDParameters) params;
		if(p.td==0 && p.N==0) {
			ad=0;
		} else {
			double H = ((double) RegulatorThread.h)/1000.0;
			ad = p.td/(p.td+p.N*H);
		}
		if (!p.integratorOn) {
			I = 0.0;
		}
		
		bd = p.k * ad * p.N;
	}
	
	@Override
	public synchronized void newParameters(Parameters p) {
		super.newParameters(p);
		recalculate();
	}

	@Override
	public synchronized double calculateSignal(double[] accel, double angularVelocity, double angle) {
		PIDParameters p = (PIDParameters) params;
		double ref = 0;
		double y = angle;
		e = ref - y;
		D = ad*D - bd*(y - yOld);
		v = p.k * (p.Beta*ref - y) + I + D; 
        yOld = y; // Could be moved to updateState() but not really needed (same #assign)
        //SegwayMain.printToScreen("ad " + ad, "bd "+bd, "u "+v, "I "+I,"D "+D);
        return v; 
	}

	@Override
	public synchronized void updateState(double u, double y) {
		PIDParameters p = (PIDParameters) params;
		double H = ((double) RegulatorThread.h) / 1000.0;
        I = p.integratorOn ? I + (p.k*H/p.ti)*e + (H/p.tr)*(u - v): 0.0;
		setSignals(new Signals((double)(System.currentTimeMillis() - startTime) / 1000.0, u, y, p));
	}

}
