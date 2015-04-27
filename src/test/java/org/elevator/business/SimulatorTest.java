package org.elevator.business;

import org.elevator.common.Constants;
import org.elevator.common.FloorCall;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public class SimulatorTest {
    public static void main(String[] args) {
        Building building = new Building();
        Elevator elevator1 = new Elevator(1, building);
        Elevator elevator2 = new Elevator(2, building);

        building.registerElevator(elevator1);
        building.registerElevator(elevator2);

        Thread tElevator1 = new Thread(elevator1);
        Thread tElevator2 = new Thread(elevator2);

        tElevator1.start();
        tElevator2.start();

        building.addFloorCall(6, false);
        building.addFloorCall(1, true);
        building.addFloorCall(2, true);
        building.addFloorCall(2, false);
        building.addFloorCall(3  , false);


    }
}

