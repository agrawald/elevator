package org.test.elevator.api;

/**
 * Elevator Management Software to implement this interface to receive events from the hardware.
 * 
 * @author jrickards
 */
public interface ElevatorCallback {

	/**
	 * Notifies that an elevator arrived on a specific floor.
	 *  
	 * @param floor
	 */
	public void elevatorArrived(int floor);
}
