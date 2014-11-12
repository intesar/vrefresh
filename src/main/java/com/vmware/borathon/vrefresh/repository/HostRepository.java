/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.repository;

/**
 *
 * @author mdshannan
 */
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vmware.borathon.vrefresh.entity.Host;

public interface HostRepository extends CrudRepository<Host, Long> {

	@Override
	List<Host> findAll();
}
