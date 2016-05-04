package segway;

public interface Regulator {
	
	/**
	 * Calculates the control signal that is to be sent to the motor
	 * 
	 * @param accel 0 is up/down, 1 is front/back, 2 is side/side
	 * @param angularVelocity angularVelocity from gyro
	 * @return
	 */
	public double calculateSignal(double[] accel, double angularVelocity, double angle);
	
	/**
	 * Updates the current state based on the previously calculates control signal
	 * and the last measurement signal. The two methods are divided to decrease response time
	 * of control action. 
	 * @return 
	 */
	public void updateState(double u, double y);

}
