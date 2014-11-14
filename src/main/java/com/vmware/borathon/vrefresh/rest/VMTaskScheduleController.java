/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.borathon.vrefresh.entity.VMTaskSchedule;
import com.vmware.borathon.vrefresh.services.VMTaskScheduleService;

/**
 *
 * @author mdshannan
 */
@RestController()
public class VMTaskScheduleController {

    @Autowired
    private VMTaskScheduleService personService;

    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public List<VMTaskSchedule> getAll() {
        return personService.getAll();
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public VMTaskSchedule create(@RequestBody VMTaskSchedule person) {
        return personService.create(person);
    }

    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.GET)
    public VMTaskSchedule findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.PUT)
    public VMTaskSchedule update(@RequestBody VMTaskSchedule request) {
        return personService.create(request);
    }
}
