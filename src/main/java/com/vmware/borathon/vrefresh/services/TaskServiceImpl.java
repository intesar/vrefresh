/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.borathon.vrefresh.entity.Task;
import com.vmware.borathon.vrefresh.entity.VCenter;
import com.vmware.borathon.vrefresh.repository.TaskRepository;

/**
 *
 * @author mdshannan
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository vCenterRepository;

    @Override
    public Task create(Task person) {
        return vCenterRepository.save(person);
    }

    @Override
    public List<Task> getAll() {
        return vCenterRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return vCenterRepository.findOne(id);
    }
}
