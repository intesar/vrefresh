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

import com.vmware.borathon.vrefresh.entity.Host;
import com.vmware.borathon.vrefresh.entity.Task;
import com.vmware.borathon.vrefresh.entity.VMTask;
import com.vmware.borathon.vrefresh.repository.VMTaskRepository;
import com.vmware.borathon.vrefresh.util.Util;

/**
 *
 * @author mdshannan
 */
@Service
@Transactional
public class VMTaskServiceImpl implements VMTaskService {

    @Autowired
    private VMTaskRepository vCenterRepository;

    @Override
    public VMTask create(VMTask person) {
        return vCenterRepository.save(person);
    }

    @Override
    public List<VMTask> getAll() {
        return vCenterRepository.findAll();
    }

    @Override
    public VMTask findById(Long id) {
        return vCenterRepository.findOne(id);
    }
    
    @Override
    public void process(VMTask vmTask) {
    	// procces task
    	// set the start-time, end-time, error, result, status (as complete, processing)
    	// save the task
    	
		try {
			Util.runLinuxCommand(vmTask);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
}
