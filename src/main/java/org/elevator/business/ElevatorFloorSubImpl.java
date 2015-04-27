package org.elevator.business;

import org.elevator.common.FloorCall;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public class ElevatorFloorSubImpl implements ElevatorFloorSub {
    private String name;
    private FloorPub floorPub;
    private FloorCall currentFloorCall;
    private Queue<FloorCall> floorCallsToExecute = new ArrayDeque<FloorCall>(6);
    private final Object MUTEX = new Object();

    public ElevatorFloorSubImpl(String nm) {
        this.name = nm;
        this.currentFloorCall = new FloorCall(0, true);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean update() {

        FloorCall floorCall = (FloorCall) floorPub.getCall(this);
        if (floorCall == null) {
            System.out.println(name + ":: No floor called");
        } else {
            synchronized (MUTEX) {
                //if on the same floor
                if (currentFloorCall.equals(floorCall)) {
                    System.out.println(name + ":: on the same floor ::" + floorCall);
                    return false;
                }
                //if going up and current floor smaller that request floor
                else {
                    return floorCallsToExecute.add(floorCall);
                }
            }
        }

        return false;
    }

    @Override
    public void setFloorPub(FloorPub floorPub) {
        this.floorPub = floorPub;
    }

    @Override
    public void execute() throws InterruptedException {
        while (true) {
            synchronized (MUTEX) {
                if(floorCallsToExecute.size()>0){
                    currentFloorCall = floorCallsToExecute.remove();
                    System.out.println(name + ":: at :: " + currentFloorCall);
                }
            }

            Thread.sleep(3000);
        }
    }
}
