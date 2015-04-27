package org.elevator.service.impl;

import org.elevator.business.CallPub;
import org.elevator.service.BuildingSvc;

/**
 * Created by e7006722 on 27/04/2015.
 */
public class BuildingSvcImpl implements BuildingSvc {
    private CallPub callPub = new CallPub();

    public void up(int floorNo) {
        callPub.up(floorNo);
    }

    public void down(int floorNo) {
        callPub.down(floorNo);
    }
}
