package org.elevator.factory;

import org.elevator.business.ElevatorPub;
import org.elevator.business.ElevatorSub;

/**
 * Created by Dheeraj Agrawal on 27/04/15.
 */
public class ElevatorPubFactory {
    public ElevatorPub createElevatorPub(final int noOfElevators) {
        ElevatorPub elevatorPub = new ElevatorPub();

        for (int i = 0; i < noOfElevators; i++) {
            ElevatorSub elevatorSub = new ElevatorSub(i + 1, elevatorPub);
            elevatorPub.register(elevatorSub);
        }
        System.out.println("@@@@@ Init factory bean");
        return elevatorPub;
    }
}
