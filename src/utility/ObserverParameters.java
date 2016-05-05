package utility;

public class ObserverParameters extends Parameters {
	/**
	 * Change this if fields are changed 
	 * Used to keep track of different ways of serializing this class
	 * E.g. if we change the order of the fields we need to change 
	 * serialVersion, otherwise there will be errors
	 */
	private static final long serialVersionUID = -5522847640263865387L;
	

	final static double DEFAULT_L1 = 6.2159;
	final static double DEFAULT_L2 = 0.3382;
	final static double DEFAULT_LR = 0.2379;

	final static double DEFAULT_K1 = 0.9343;
	final static double DEFAULT_K2 = 11.2167;// 74.2533
	final static double DEFAULT_KV = 0.4205;// 60.9601

	final static boolean DEFAULT_INTEGRATOR_ON = true;

	final static double DEFAULT_R = 0;
	
	public double K1;
	public double K2;
	public double L1;
	public double L2;
	public double Lr;
	public double Kv;
	public boolean integratorOn;
	
	public ObserverParameters() {
		K1 = DEFAULT_K1;
		K2 = DEFAULT_K2;
		L1 = DEFAULT_L1;
		L2 = DEFAULT_L2;
		Lr = DEFAULT_LR;
		Kv = DEFAULT_KV;
		integratorOn = DEFAULT_INTEGRATOR_ON;
	}
	
	public ObserverParameters(double k1, double k2, double l1, double l2, double lr, double kv, boolean integratorOn) {
		K1 = k1;
		K2 = k2;
		L1 = l1;
		L2 = l2;
		Lr = lr;
		Kv = kv;
		this.integratorOn = integratorOn;
	}

	/* (non-Javadoc)
	 * @see utility.Parameters#clone()
	 */
	@Override
	public Object clone() {
		return new ObserverParameters(K1, K2, L1, L2, Lr, Kv, integratorOn);
	}

	/* (non-Javadoc)
	 * @see utility.Parameters#toString()
	 */
	@Override
	public String toString() {
		return "Parameters [K1=" + K1 + ", K2=" + K2 + ", L1=" + L1 + ", L2=" + L2 + ", Lr=" + Lr + ", Kv=" + Kv
				+ ", integratorOn=" + integratorOn + "]";
	}
}
