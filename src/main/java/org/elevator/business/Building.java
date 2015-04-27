package org.elevator.business;

import org.elevator.common.Constants;
import org.elevator.common.Direction;
import org.elevator.common.FloorCall;
import org.elevator.common.Source;

import java.util.*;

/**
 * Created by Dheeraj Agrawal on 27/04/15.
 */
public class Building
{
    private final Set<FloorCall> floorCalls = new TreeSet<FloorCall>();

    public synchronized void addFloorCall(int floorNo, Direction forUp){
        FloorCall floorCall = new FloorCall(floorNo, forUp);
        if(!floorCalls.contains(floorCall)){
            floorCalls.add(floorCall);
        }
    }

    public synchronized FloorCall nextFloorCall(FloorCall currentFloorCall){
        if(floorCalls.isEmpty()) return null;

        Iterator<FloorCall> itr = floorCalls.iterator();
        while(itr.hasNext()) {
            FloorCall nextFloorCall = itr.next();
            if (Direction.UP == currentFloorCall.getDirection()
                    && Direction.UP == nextFloorCall.getDirection()
                    && currentFloorCall.getFloor() < nextFloorCall.getFloor()) {
                itr.remove();
                return nextFloorCall;
            }

            if(Direction.DOWN == currentFloorCall.getDirection()
                    && Direction.DOWN == nextFloorCall.getDirection()
                    && currentFloorCall.getFloor() > nextFloorCall.getFloor())
            {
                itr.remove();
                return nextFloorCall;
            }
        }

        itr = floorCalls.iterator();
        if(itr.hasNext()) {
            FloorCall nextFloorCall = itr.next();
            itr.remove();
            return nextFloorCall;
        }

        return null;
    }
}
