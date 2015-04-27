package org.elevator.service.impl;

import org.elevator.business.ElevatorPubSingleton;
import org.elevator.service.BuildingSvc;
import org.springframework.stereotype.Component;

/**
 * Created by e7006722 on 27/04/2015.
 */
@Component
public class BuildingSvcImpl implements BuildingSvc {
    public void up(int floorNo) {
        ElevatorPubSingleton.elevatorPub.up(floorNo);
    }

    public void down(int floorNo) {
        ElevatorPubSingleton.elevatorPub.down(floorNo);
    }
}
