package utility;

import java.io.Serializable;

public class PIDParameters extends Parameters {
	
	private static final double DEFAULT_K = 10;// Gain
	private static final double DEFAULT_TI = 0.1; // Integral weight parameter
    private static final double DEFAULT_TR = 1;// Tracking weight
   	private static final double DEFAULT_TD = 0.45; // Derivative weight
   	private static final double DEFAULT_N = 5; // Low pass filter constant for derivate
    private static final double DEFAULT_BETA = 1.0; // Set point weighting for P-part    
    
	/**
	 * Change this if fields are changed 
	 * Used to keep track of different ways of serializing this class
	 * E.g. if we change the order of the fields we need to change 
	 * serialVersion, otherwise there will be errors
	 */
	private static final long serialVersionUID = -5522847640263865387L;
	public double k;
	public double ti;
	public double tr;
	public double td;
	public double N;
	public boolean integratorOn;
	public double Beta;
	
	public PIDParameters() {
		k = DEFAULT_K;
		ti = DEFAULT_TI;
		tr = DEFAULT_TR;
		td = DEFAULT_TD;
		N = DEFAULT_N;
		Beta = DEFAULT_BETA;
		integratorOn = ti != 0;
	}
	public PIDParameters(double k, double ti, double tr, double td, double N, boolean integratorOn) {
		this.k = k;
		this.ti = ti;
		this.tr = tr;
		this.td = td;
		this.N = N;
		this.Beta = DEFAULT_BETA;
		this.integratorOn = integratorOn;
	}

	@Override
	public Object clone() {
		return new PIDParameters(k, ti, tr, td, N, integratorOn);
	}

	@Override
	public String toString() {
		return "Parameters [K=" + k + ", Ti=" + ti + ", Tr=" + tr + ", Td=" + td 
				+ ", integratorOn=" + integratorOn + "]";
	}
}
