package org.elevator.business;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public class SimulatorTest {
    public static void main(String[] args) throws InterruptedException {
        CallPub callPub = new CallPub();
        ElevatorSub elevatorSub1 = new ElevatorSub(1, callPub);
        ElevatorSub elevatorSub2 = new ElevatorSub(2, callPub);
        callPub.register(elevatorSub1);
        callPub.register(elevatorSub2);

        Thread te1 = new Thread(elevatorSub1);
        Thread te2 = new Thread(elevatorSub2);
        Thread tCallPub = new Thread(callPub);


        te1.start();
        te2.start();

        callPub.up(6);
        callPub.down(3);
        callPub.up(4);
        callPub.up(2);
        callPub.up(5);
        callPub.down(6);
        callPub.up(1);
        callPub.up(3);

        //tCallPub.start();

    }
}

