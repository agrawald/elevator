package org.elevator.business;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public interface ElevatorFloorSub {
    //method to update the observer, used by subject
    boolean update();

    //attach with subject to observe
    void setFloorPub(FloorPub floorPub);

    void execute() throws InterruptedException;
}
