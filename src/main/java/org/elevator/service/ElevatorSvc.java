package org.elevator.service;

import org.elevator.common.FloorCall;

/**
 * Created by e7006722 on 27/04/2015.
 */
public interface ElevatorSvc {
    void gotoFloor(int floorNo, int elevatorId);
    void stop(int elevatorId);

    FloorCall status(int elevatorId);
}
