package org.elevator.business;

import org.elevator.common.FloorCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public class FloorPubImpl implements FloorPub {
    private List<ElevatorFloorSub> elevatorFloorSubs;
    private FloorCall floorCall;
    private boolean changed;
    private final Object MUTEX= new Object();

    public FloorPubImpl(){
        this.elevatorFloorSubs =new ArrayList<ElevatorFloorSub>();
    }
    @Override
    public void register(ElevatorFloorSub obj) {
        if(obj == null) throw new NullPointerException("Null Elevator");
        synchronized (MUTEX) {
            if(!elevatorFloorSubs.contains(obj)) elevatorFloorSubs.add(obj);
        }
    }

    @Override
    public void unregister(ElevatorFloorSub obj) {
        synchronized (MUTEX) {
            elevatorFloorSubs.remove(obj);
        }
    }

    @Override
    public void notifyElevators() {
        List<ElevatorFloorSub> elevatorsLocal = null;
        //synchronization is used to make sure any Elevator registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            elevatorsLocal = new ArrayList<ElevatorFloorSub>(this.elevatorFloorSubs);
            this.changed=false;
        }
        for (ElevatorFloorSub obj : elevatorsLocal) {
            if(obj.update()){
                System.out.println("FLOOR :: " + obj + " :: On the way");
                break;
            }
        }

    }

    @Override
    public Object getCall(ElevatorFloorSub obj) {
        return this.floorCall;
    }

    @Override
    //method to post message to the topic
    public void call(FloorCall floorCall){
        System.out.println("FLOOR:"+floorCall);
        this.floorCall=floorCall;
        this.changed=true;
        notifyElevators();
    }
}
