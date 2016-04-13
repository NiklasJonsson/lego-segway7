package utility;

import java.io.Serializable;

public class Signals implements Cloneable, Serializable {
	/**
	 * Change this if fields are changed 
	 * Used to keep track of different ways of serializing this class
	 * E.g. if we change the order of the fields we need to change 
	 * serialVersion, otherwise there will be errors
	 */
	private static final long serialVersionUID = -5522847640263865387L;
	public double u;
	public double y;
	public double x1;
	public double x2;
	public double l1;
	public double l2;
	public double lr;


	public Signals(double u, double y, double x1, double x2, double l1, double l2, double lr) {
		this.u = u;
		this.y = y;
		this.x1 = x1;
		this.x2 = x2;
		this.l1 = l1;
		this.l2 = l2;
		this.lr = lr;
	}

	@Override
	public Object clone() {
		return new Signals(u,y,x1,x2,l1,l2,lr);
	}
	
	@Override
	public String toString() {
		return "Signals [u=" + u + ", y=" + y + ", x1=" + x1 + ", x2=" + x2 + ", l1=" + l1 + ", l2=" + l2 + ", lr=" + lr
				+ "]";
	}
	
}