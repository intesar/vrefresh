/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.services;

import java.util.List;

import com.vmware.borathon.vrefresh.entity.VMTask;

/**
 *
 * @author mdshannan
 */
public interface VMTaskService {

	VMTask create(VMTask person);

	List<VMTask> getAll();

    public VMTask findById(Long id);
    
    public void process(VMTask task);

}
