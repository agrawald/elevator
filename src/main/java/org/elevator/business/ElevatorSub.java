package org.elevator.business;

import org.elevator.common.FloorCall;
import org.test.elevator.api.ElevatorCallback;
import org.test.elevator.api.ElevatorFacade;
import org.test.elevator.api.ElevatorFactory;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by e7006722 on 27/04/2015.
 */
public class ElevatorSub implements Runnable {
    private int elevatorId;
    private boolean up = true;
    private int currentFloor = 1;
    private ElevatorFacade elevatorFacade;
    private boolean running = true;
    private Queue<FloorCall> floorCalls = new ArrayDeque<FloorCall>();
    private FloorCall nextFloorCall;
    private final Object MUTEX = new Object();
    private CallPub callPub;

    public ElevatorSub(final int elevatorId, final CallPub callPub) {
        this.elevatorId = elevatorId;
        this.callPub = callPub;
        this.elevatorFacade = ElevatorFactory.getElevatorFacade(elevatorId, new ElevatorCallback() {
            @Override
            public void elevatorArrived(int floor) {
                currentFloor = floor;
                if (floor == nextFloorCall.getFloorNo()) {
                    System.out.println(elevatorId + ": arrived :" + currentFloor + ": up :" + up);
                    elevatorFacade.lockBreaks();
                }
            }
        });
    }

    public void update(FloorCall floorCall) {
        synchronized (MUTEX) {
            floorCalls.add(floorCall);
        }
    }

    public void stop() {
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

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public void run() {
        while (running) {
            do {
                nextFloorCall = callPub.getUpdate();
                if (nextFloorCall != null) {
                    if (this.currentFloor != nextFloorCall.getFloorNo()) {
                        this.elevatorFacade.unlockBreaks();
                        while (this.currentFloor > nextFloorCall.getFloorNo()) {
                            this.elevatorFacade.moveDownOneFloor();
                        }

                        while (this.currentFloor < nextFloorCall.getFloorNo()) {
                            this.elevatorFacade.moveUpOneFloor();
                        }
                    } else {
                        if(nextFloorCall.isUp()) callPub.up(nextFloorCall.getFloorNo());
                        else callPub.down(nextFloorCall.getFloorNo());
                    }
                }
            } while (nextFloorCall != null);

            try {
                System.out.println(elevatorId + ": sleeping...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
