package utility;

import java.io.Serializable;

public class Parameters implements Cloneable, Serializable {
	/**
	 * Change this if fields are changed 
	 * Used to keep track of different ways of serializing this class
	 * E.g. if we change the order of the fields we need to change 
	 * serialVersion, otherwise there will be errors
	 */
	private static final long serialVersionUID = -5522847640263865387L;
	public double K1;
	public double K2;
	public double L1;
	public double L2;
	public double Lr;
	public double Kv;
	public double R;
	public boolean integratorOn;
	public Parameters(double k1, double k2, double l1, double l2, double lr, double kv, double r,
			boolean integratorOn) {
		super();
		K1 = k1;
		K2 = k2;
		L1 = l1;
		L2 = l2;
		Lr = lr;
		Kv = kv;
		R = r;
		this.integratorOn = integratorOn;
	}
	@Override
	public String toString() {
		return "Parameters [K1=" + K1 + ", K2=" + K2 + ", L1=" + L1 + ", L2=" + L2 + ", Lr=" + Lr + ", Kv=" + Kv
				+ ", R=" + R + ", integratorOn=" + integratorOn + "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Parameters(K1, K2, L1, L2, Lr, Kv, R, integratorOn);
	}

}
