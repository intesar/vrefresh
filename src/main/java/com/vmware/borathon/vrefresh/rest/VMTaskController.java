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

import com.vmware.borathon.vrefresh.entity.VMTask;
import com.vmware.borathon.vrefresh.services.VMTaskService;

/**
 *
 * @author mdshannan
 */
@RestController()
public class VMTaskController {

    @Autowired
    private VMTaskService personService;

    @RequestMapping(value = "/vmtasks", method = RequestMethod.GET)
    public List<VMTask> getAll() {
        return personService.getAll();
    }

    @RequestMapping(value = "/vmtasks", method = RequestMethod.POST)
    public VMTask create(@RequestBody VMTask person) {
        return personService.create(person);
    }

    @RequestMapping(value = "/vmtasks/{id}", method = RequestMethod.GET)
    public VMTask findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "/vmtasks/{id}", method = RequestMethod.PUT)
    public VMTask update(@RequestBody VMTask request) {
        return personService.create(request);
    }
}
