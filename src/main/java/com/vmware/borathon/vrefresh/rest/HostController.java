/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.borathon.vrefresh.entity.Host;
import com.vmware.borathon.vrefresh.entity.Task;
import com.vmware.borathon.vrefresh.entity.VCenter;
import com.vmware.borathon.vrefresh.services.HostService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author mdshannan
 */
@RestController()
public class HostController {

    @Autowired
    private HostService personService;

    @RequestMapping(value = "/hosts", method = RequestMethod.GET)
    public List<Host> getAll() {
        return personService.getAll();
    }

    @RequestMapping(value = "/hosts", method = RequestMethod.POST)
    public Host create(@RequestBody Host person) {
        return personService.create(person);
    }

    @RequestMapping(value = "/hosts/{id}", method = RequestMethod.GET)
    public Host findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "/hosts/{id}", method = RequestMethod.PUT)
    public Host update(@RequestBody Host request) {
        return personService.create(request);
    }
}
