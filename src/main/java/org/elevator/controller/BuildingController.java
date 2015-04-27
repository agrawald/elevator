package org.elevator.controller;

import org.elevator.service.BuildingSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dheeraj Agrawal on 27/04/15.
 */
@Controller
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingSvc buildingSvc;

    @RequestMapping(value = "/up/{floorNo}", method = RequestMethod.PUT)
    public void up(@PathVariable int floorNo) {
        buildingSvc.up(floorNo);
    }

    @RequestMapping(value = "/down/{floorNo}", method = RequestMethod.PUT)
    public void down(@PathVariable int floorNo) {
        buildingSvc.down(floorNo);
    }
}
