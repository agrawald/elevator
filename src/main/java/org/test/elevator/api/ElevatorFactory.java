package org.test.elevator.api;

import java.util.HashMap;
import java.util.Map;

/**
 * This factory constructs a concrete version of org.test.elevator.api.ElevatorFacade interface.
 *   
 * @author jrickards
 */
public class ElevatorFactory {
	
	private static Map<Integer,ElevatorFacade> elevatorFacades = new HashMap<Integer,ElevatorFacade>();
	
	/**
	 * Returns an instance of the class used to interface with the elevator.
	 *  
	 * @param elevatorID the elevator number
	 * @param callback a class to notify completion of a task
	 * @return
	 */
	public static ElevatorFacade getElevatorFacade(Integer elevatorID, ElevatorCallback callback) {
		ElevatorFacade f = elevatorFacades.get(elevatorID);
		if (f==null) {
			//TODO: Change this to lookup the class that does the RMI call to hardware.
			f = new ElevatorFacadeStub(callback);
			elevatorFacades.put(elevatorID, f);
		}
		return f;
	}

	
}
