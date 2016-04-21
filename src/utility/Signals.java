package utility;

import java.io.Serializable;

public class Signals implements Cloneable, Serializable {
	/**
	 * Change this if fields are changed Used to keep track of different ways of
	 * serializing this class E.g. if we change the order of the fields we need
	 * to change serialVersion, otherwise there will be errors
	 */
	private static final long serialVersionUID = -5522847640263865387L;
	public double sampleTime;
	public double u;
	public double y;
	public double x1;
	public double x2;
	public Parameters parameters;

	public Signals(double sampleTime, double u, double y, double x1, double x2, Parameters parameters) {
		this.sampleTime = sampleTime;
		this.u = u;
		this.y = y;
		this.x1 = x1;
		this.x2 = x2;
		this.parameters = (Parameters) parameters.clone();
	}

	@Override
	public Object clone() {
		return new Signals(sampleTime, u, y, x1, x2, (Parameters) parameters.clone());
	}

	@Override
	public String toString() {
		return "Signals [sampleTime=" + sampleTime + ", u=" + u + ", y=" + y + ", x1=" + x1 + ", x2=" + x2
				+ ", parameters=" + parameters + "]";
	}

}