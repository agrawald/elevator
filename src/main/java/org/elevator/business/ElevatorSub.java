package org.elevator.business;

import org.elevator.common.FloorCall;
import org.test.elevator.api.ElevatorCallback;
import org.test.elevator.api.ElevatorFacade;
import org.test.elevator.api.ElevatorFactory;

/**
 * Created by e7006722 on 27/04/2015.
 */
public class ElevatorSub extends Thread {
    private int elevatorId;
    private boolean up = true;
    private int currentFloor = 1;
    private ElevatorFacade elevatorFacade;
    private boolean running = true;
    private FloorCall nextFloorCall;
    private ElevatorPub elevatorPub;

    public ElevatorSub(final int elevatorId, final ElevatorPub elevatorPub) {
        this.elevatorId = elevatorId;
        this.elevatorPub = elevatorPub;
        this.elevatorFacade = ElevatorFactory.getElevatorFacade(elevatorId, new ElevatorCallback() {
            public void elevatorArrived(int floor) {
                currentFloor = floor;
                if (nextFloorCall != null && floor == nextFloorCall.getFloorNo()) {
                    System.out.println(elevatorId + ": arrived :" + currentFloor + ": up :" + up);
                    elevatorFacade.lockBreaks();
                }
            }
        });

        Thread tElevator = new Thread(this);
        tElevator.start();
    }

    public void terminate() {
        running = false;
        elevatorFacade.lockBreaks();
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public FloorCall getCurrentFloor() {
        return new FloorCall(currentFloor, up);
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void run() {
        while (running) {
            nextFloorCall = elevatorPub.getUpdate();
            while (nextFloorCall != null) {
                if (this.currentFloor != nextFloorCall.getFloorNo()) {
                    this.elevatorFacade.unlockBreaks();
                    while (this.currentFloor > nextFloorCall.getFloorNo()) {
                        this.elevatorFacade.moveDownOneFloor();
                    }

                    while (this.currentFloor < nextFloorCall.getFloorNo()) {
                        this.elevatorFacade.moveUpOneFloor();
                    }
                } else {
                    if (nextFloorCall.isUp()) elevatorPub.up(nextFloorCall.getFloorNo());
                    else elevatorPub.down(nextFloorCall.getFloorNo());
                }

                nextFloorCall = elevatorPub.getUpdate();
            }

            try {
                //System.out.println(elevatorId + ": Waiting...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
