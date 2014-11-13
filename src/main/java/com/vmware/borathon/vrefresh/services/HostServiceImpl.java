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
import com.vmware.borathon.vrefresh.repository.HostRepository;

/**
 *
 * @author mdshannan
 */
@Service
@Transactional
public class HostServiceImpl implements HostService {

    @Autowired
    private HostRepository vCenterRepository;

    @Override
    public Host create(Host person) {
        return vCenterRepository.save(person);
    }

    @Override
    public List<Host> getAll() {
        return vCenterRepository.findAll();
    }

    @Override
    public Host findById(Long id) {
        return vCenterRepository.findOne(id);
    }
}
