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

import com.vmware.borathon.vrefresh.entity.Task;
import com.vmware.borathon.vrefresh.services.TaskService;

/**
 *
 * @author mdshannan
 */
@RestController()
public class TaskController {

	@Autowired
	private TaskService personService;

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public List<Task> getAll() {
		return personService.getAll();
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public Task create(@RequestBody Task person) {
		return personService.create(person);
	}
}
