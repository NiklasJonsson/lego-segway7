package Computer;

import java.io.Serializable;

public class Parameters implements Cloneable, Serializable {
	/**
	 * Change this if fields are changed 
	 * Used to keep track of different ways of serializing this class
	 * E.g. if we change the order of the fields we need to change 
	 * serialVersion, otherwise there will be errors
	 */
	private static final long serialVersionUID = -5522847640263865387L;
	public double K;
	public double Ti;
	public double Tr;
	public double Td;
	public double N;
	public double Beta;
	public double H;
	public boolean integratorOn;
	
	public Parameters(double k, double ti, double tr, double td, double n, double beta, double h,
			boolean integratorOn) {
		super();
		K = k;
		Ti = ti;
		Tr = tr;
		Td = td;
		N = n;
		Beta = beta;
		H = h;
		this.integratorOn = integratorOn;
	}
	
	@Override
	public Object clone() {
		return new Parameters(K, Ti, Tr, Td, N, Beta, H, integratorOn);
	}

	@Override
	public String toString() {
		return "Parameters [K=" + K + ", Ti=" + Ti + ", Tr=" + Tr + ", Td=" + Td + ", N=" + N + ", Beta=" + Beta
				+ ", H=" + H + ", integratorOn=" + integratorOn + "]";
	}

}
