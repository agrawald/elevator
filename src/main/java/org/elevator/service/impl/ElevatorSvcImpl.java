package org.elevator.service.impl;

import org.elevator.business.StartElevators;
import org.elevator.common.FloorCall;
import org.elevator.service.ElevatorSvc;
import org.springframework.stereotype.Component;

/**
 * Created by e7006722 on 27/04/2015.
 */
@Component
public class ElevatorSvcImpl implements ElevatorSvc {

    public void gotoFloor(int floorNo, int elevatorId) {
        StartElevators.elevatorPub.gotoFloor(floorNo, elevatorId);
    }

    public void stop(int elevatorId){
        StartElevators.elevatorPub.stop(elevatorId);
    }

    public FloorCall status(int elevatorId) {
        return StartElevators.elevatorPub.status(elevatorId);
    }
}
