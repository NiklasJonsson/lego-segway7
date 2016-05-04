package utility;

import java.io.Serializable;

public class PIDParameters implements Cloneable, Serializable {
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
	public boolean integratorOn;
	public double r;
	
	public PIDParameters(double k, double ti, double tr, double td, boolean integratorOn, double r) {
		this.k = k;
		this.ti = ti;
		this.tr = tr;
		this.td = td;
		this.integratorOn = integratorOn;
		this.r = r;
	}

	@Override
	public Object clone() {
		return new PIDParameters(k, ti, tr, td, integratorOn, r);
	}

	@Override
	public String toString() {
		return "Parameters [K=" + k + ", Ti=" + ti + ", Tr=" + tr + ", Td=" + td 
				+ ", integratorOn=" + integratorOn + ", r=" + r + "]";
	}
}
