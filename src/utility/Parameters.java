package utility;

import java.io.Serializable;

public abstract class Parameters implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6339889984149672280L;
	public final int R = 0;

	public abstract Object clone();

	public abstract String toString();

}