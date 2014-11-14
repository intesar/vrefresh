/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author mdshannan
 */
@Entity
public class Host implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String hostType;
	private String host;
	private String username;
	private String password;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private VCenter vcenter;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name="host_id")
	private List<VMTask> tasks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public VCenter getVcenter() {
		return vcenter;
	}

	public void setVcenter(VCenter vcenter) {
		this.vcenter = vcenter;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<VMTask> getTasks() {
		for (VMTask t : tasks) {
			t.setHost(null);
		}
		return tasks;
	}

	public void setTasks(List<VMTask> tasks) {
		
		this.tasks = tasks;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Host)) {
			return false;
		}
		Host other = (Host) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "demo.entity.Person[ id=" + id + " ]";
	}

}
