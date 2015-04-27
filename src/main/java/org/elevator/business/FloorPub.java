package org.elevator.business;

import org.elevator.common.FloorCall;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public interface FloorPub {
    //methods to register and unregister observers
    void register(ElevatorFloorSub obj);

    void unregister(ElevatorFloorSub obj);

    //method to notify observers of change
    void notifyElevators();

    //method to get updates from subject
    Object getCall(ElevatorFloorSub obj);

    void call(FloorCall floorCall);
}
