package org.elevator.controller;

import org.elevator.common.FloorCall;
import org.elevator.service.ElevatorSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Dheeraj Agrawal on 27/04/15.
 */
@Controller
@RequestMapping("/elevator")
public class ElevatorController {
    @Autowired
    private ElevatorSvc elevatorSvc;

    @RequestMapping(value = "/{elevatorId}/goto/{floorNo}", method = RequestMethod.PUT)
    public void gotoFloor(@PathVariable int elevatorId, @PathVariable int floorNo) {
        System.out.println("FloorNo:" + floorNo + " ElevatorId " + elevatorId);
        elevatorSvc.gotoFloor(floorNo, elevatorId);
    }

    @RequestMapping(value = "/{elevatorId}/status", method = RequestMethod.GET)
    public
    @ResponseBody
    FloorCall status(@PathVariable int elevatorId) {
        return elevatorSvc.status(elevatorId);
    }

    @RequestMapping(value = "/{elevatorId}/stop", method = RequestMethod.PUT)
    public void stop(@PathVariable int elevatorId) {
        elevatorSvc.stop(elevatorId);
    }

    @RequestMapping(value = "/elevator", method = RequestMethod.GET)
    public String printWelcome() {
        return "index";
    }
}
