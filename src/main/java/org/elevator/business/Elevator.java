package org.elevator.business;

import org.elevator.common.Direction;
import org.elevator.common.FloorCall;

/**
 * Created by Dheeraj Agrawal on 27/04/15.
 */
public class Elevator implements Runnable {
    private int elevatorId;
    private Building building;
    private ElevatorCalls elevatorCalls = new ElevatorCalls();
    private FloorCall currentFloorCall;
    private final Object MUTEX = new Object();

    public Elevator(int elevatorId, Building building) {
        this.elevatorId = elevatorId;
        this.building = building;
        this.currentFloorCall = new FloorCall(1, Direction.UP);
    }

    public void addElevatorCall(int floorNo){
        elevatorCalls.addFloorCall(floorNo, currentFloorCall.getDirection());
    }

    @Override
    public void run() {
        while (true) {
            FloorCall nextFloorCall = null;
            FloorCall nextBuildingFloorCall = building.nextFloorCall(currentFloorCall);
            FloorCall nextElevatorCall = elevatorCalls.nextFloorCall(currentFloorCall);

            if(nextBuildingFloorCall!=null && nextElevatorCall!=null){
                if(nextBuildingFloorCall.equals(nextElevatorCall))
                    nextFloorCall = nextBuildingFloorCall;
                else

            } else if(nextBuildingFloorCall!=null && nextElevatorCall==null){

            } else {

            }
            else if(nextBuildingFloorCall!=null && next){
                currentFloorCall = nextBuildingFloorCall;
                System.out.println(elevatorId + " - Servicing..." + currentFloorCall);
            }

            try {
                System.out.println(elevatorId + " -  Sleeping");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println(elevatorId + " -  Error while sleeping " + e.getMessage());
            }
        }
    }



    @Override
    public String toString() {
        return "Elevator{" +
                "elevatorId=" + elevatorId +
                '}';
    }
}
