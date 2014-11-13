/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.services;

import java.util.List;

import com.vmware.borathon.vrefresh.entity.Host;
import com.vmware.borathon.vrefresh.entity.VCenter;

/**
 *
 * @author mdshannan
 */
public interface HostService {

	Host create(Host person);

	List<Host> getAll();

    public Host findById(Long id);

}
