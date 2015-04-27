package org.elevator.business;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public class SimulatorTest {
    public static void main(String[] args) throws InterruptedException {
        ElevatorPub elevatorPub = new ElevatorPub();
        ElevatorSub elevatorSub1 = new ElevatorSub(1, elevatorPub);
        ElevatorSub elevatorSub2 = new ElevatorSub(2, elevatorPub);
        elevatorPub.register(elevatorSub1);
        elevatorPub.register(elevatorSub2);

        Thread te1 = new Thread(elevatorSub1);
        Thread te2 = new Thread(elevatorSub2);


        te1.start();
        te2.start();

        elevatorPub.up(6);
        elevatorPub.down(3);
        elevatorPub.up(4);
        elevatorPub.up(2);
        elevatorPub.up(5);
        elevatorPub.down(6);
        elevatorPub.up(1);
        elevatorPub.up(3);
        elevatorPub.gotoFloor(2, 1);

        //tCallPub.start();

    }
}

