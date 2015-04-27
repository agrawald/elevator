package org.test.elevator.api;

/**
 * A Stub implementation of org.test.elevator.api.ElevatorFacade.
 * 
 * @author jrickards
 */
public class ElevatorFacadeStub implements ElevatorFacade {
	
	private boolean breaksOn = false;
	private int currentFloor = 0;
	private final ElevatorCallback callback;
	
	public ElevatorFacadeStub(ElevatorCallback callback) {
		if (callback == null) throw new IllegalArgumentException("callback can not be null");
		this.callback = callback;
	}
	
	@Override
	public void moveUpOneFloor() {
		System.out.println("Current Floor " + ++currentFloor);
		callback.elevatorArrived(currentFloor);
	}

	@Override
	public void moveDownOneFloor() {
		System.out.println("Current Floor " + --currentFloor);
		callback.elevatorArrived(currentFloor);
	}

	@Override
	public void lockBreaks() {
		breaksOn = true;
		System.out.println("breaksOn=" + breaksOn);
	}

	@Override
	public void unlockBreaks() {
		breaksOn = false;
		System.out.println("breaksOn=" + breaksOn);
	}

}
