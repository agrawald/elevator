package org.elevator.service.impl;

import org.elevator.business.CallPub;
import org.elevator.service.ElevatorSvc;

/**
 * Created by e7006722 on 27/04/2015.
 */
public class ElevatorSvcImpl implements ElevatorSvc {
    private CallPub callPub = new CallPub();

    public void gotoFloor(int floorNo, int elevatorId) {
        callPub.gotoFloor(floorNo, elevatorId);
    }

    public void stop(int elevatorId){
        callPub.stop(elevatorId);
    }

    public int status(int elevatorId){
        return callPub.status(elevatorId);
    }
}
