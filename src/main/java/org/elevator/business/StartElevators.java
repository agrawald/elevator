package org.elevator.business;

import org.elevator.common.Constants;
import org.springframework.stereotype.Component;

/**
 * Created by Dheeraj Agrawal on 27/04/15.
 */
@Component
public class StartElevators {
    public final static ElevatorPub elevatorPub = createElevatorPub();

    public static ElevatorPub createElevatorPub() {
        ElevatorPub elevatorPub = new ElevatorPub();

        for (int i = 0; i < Constants.MAX_ELEVATORS; i++) {
            System.out.println(" Inti Elevator  " + (i + 1));
            ElevatorSub elevatorSub = new ElevatorSub(i + 1, elevatorPub);
            elevatorPub.register(elevatorSub);
            elevatorSub.start();
        }

        System.out.println("Starting the elevators");
        return elevatorPub;
    }
}
