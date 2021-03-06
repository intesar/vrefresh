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

import com.vmware.borathon.vrefresh.entity.VCenter;
import com.vmware.borathon.vrefresh.services.VCenterService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author mdshannan
 */
@RestController()
public class VCenterController {

    @Autowired
    private VCenterService personService;

    @RequestMapping(value = "/vcenters", method = RequestMethod.GET)
    public List<VCenter> getAll() {
        return personService.getAll();
    }

    @RequestMapping(value = "/vcenters/{id}", method = RequestMethod.GET)
    public VCenter findById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "/vcenters", method = RequestMethod.POST)
    public VCenter create(@RequestBody VCenter person) {
        return personService.create(person);
    }

    @RequestMapping(value = "/vcenters/{id}", method = RequestMethod.PUT)
    public VCenter update(@RequestBody VCenter request) {
        return personService.create(request);
    }

}
