package org.elevator.business;

import org.elevator.common.Constants;
import org.elevator.common.FloorCall;

import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by e7006722 on 27/04/2015.
 */
public class ElevatorPub {
    private final Object MUTEX = new Object();
    private List<ElevatorSub> elevatorSubs = new ArrayList<ElevatorSub>();
    private Queue<FloorCall> pendingFloorCalls = new ArrayDeque<FloorCall>();

    public void register(ElevatorSub... elevatorSubs) {
        for (ElevatorSub elevatorSub : elevatorSubs)
            this.elevatorSubs.add(elevatorSub);
    }

    public void up(int floorNo) {
        if (floorNo > 0 && floorNo <= Constants.MAX_FLOORS) {
            FloorCall floorCall = new FloorCall(floorNo, true);
            synchronized (MUTEX) {
                if (!pendingFloorCalls.contains(floorCall)) pendingFloorCalls.add(floorCall);
            }
        } else throw new InvalidParameterException("Invalid floor: " + floorNo);
    }

    public void down(int floorNo) {
        if (floorNo > 0 && floorNo <= Constants.MAX_FLOORS) {
            FloorCall floorCall = new FloorCall(floorNo, false);
            synchronized (MUTEX) {
                if (!pendingFloorCalls.contains(floorCall)) pendingFloorCalls.add(floorCall);
            }
        } else throw new InvalidParameterException("Invalid floor: " + floorNo);
    }

    public void stop(int elevatorId) {
        for (ElevatorSub elevatorSub : elevatorSubs) {
            if (elevatorId == elevatorSub.getElevatorId()) {
                elevatorSub.terminate();
                break;
            }
        }
    }

    public void gotoFloor(int floorNo, int elevatorId) {
        for (ElevatorSub elevatorSub : elevatorSubs) {
            if (elevatorId == elevatorSub.getElevatorId()) {
                if (elevatorSub.isUp() && elevatorSub.getCurrentFloor().getFloorNo() < floorNo) {
                    FloorCall floorCall = new FloorCall(floorNo, true);
                    synchronized (MUTEX) {
                        if (!pendingFloorCalls.contains(floorCall)) pendingFloorCalls.add(floorCall);
                    }
                } else if (!elevatorSub.isUp() && elevatorSub.getCurrentFloor().getFloorNo() > floorNo) {
                    FloorCall floorCall = new FloorCall(floorNo, false);
                    synchronized (MUTEX) {
                        if (!pendingFloorCalls.contains(floorCall)) pendingFloorCalls.add(floorCall);
                    }
                } else throw new InvalidParameterException("Floor cannot be selected: " + floorNo);
                break;
            }
        }
    }

    public synchronized FloorCall getUpdate() {
        synchronized (MUTEX) {
            if (!pendingFloorCalls.isEmpty())
                return pendingFloorCalls.remove();
        }
        return null;
    }

    public FloorCall status(int elevatorId) {
        for (ElevatorSub elevatorSub : elevatorSubs) {
            if (elevatorId == elevatorSub.getElevatorId())
                return elevatorSub.getCurrentFloor();
        }

        throw new InvalidParameterException("Invalid elevator id: " + elevatorId);
    }
}
