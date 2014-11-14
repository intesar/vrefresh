/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.services;

import java.util.List;

import com.vmware.borathon.vrefresh.entity.VMTaskSchedule;

/**
 *
 * @author mdshannan
 */
public interface VMTaskScheduleService {

	VMTaskSchedule create(VMTaskSchedule person);

	List<VMTaskSchedule> getAll();

    public VMTaskSchedule findById(Long id);
    
    public void processSchedule();

}
