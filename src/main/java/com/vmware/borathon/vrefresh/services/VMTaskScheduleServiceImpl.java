/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmware.borathon.vrefresh.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.borathon.vrefresh.entity.VMTask;
import com.vmware.borathon.vrefresh.entity.VMTaskSchedule;
import com.vmware.borathon.vrefresh.repository.VMTaskScheduleRepository;

/**
 *
 * @author mdshannan
 */
@Service
@Transactional
public class VMTaskScheduleServiceImpl implements VMTaskScheduleService {

	@Autowired
	private VMTaskScheduleRepository vMTaskScheduleRepository;

	@Autowired
	private VMTaskService service;

	@Override
	public VMTaskSchedule create(VMTaskSchedule person) {
		return vMTaskScheduleRepository.save(person);
	}

	@Override
	public List<VMTaskSchedule> getAll() {
		return vMTaskScheduleRepository.findAll();
	}

	@Override
	public VMTaskSchedule findById(Long id) {
		return vMTaskScheduleRepository.findOne(id);
	}

	@Async
	public void processAsync(VMTask task) {
		service.process(task);
		service.create(task);
	}

	@Scheduled(fixedRate = 1000 * 59)
	public void processSchedule() {
		System.out.println("schedular fired");

		String day = String.valueOf(Calendar.getInstance().get(
				Calendar.DAY_OF_WEEK));
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 100;
		String dayTime = String.valueOf(hour
				+ Calendar.getInstance().get(Calendar.MINUTE));
		System.out.println("day : " + day + " hour : " + hour + " time: "
				+ dayTime);
		List<VMTaskSchedule> list = vMTaskScheduleRepository.findByDayAndDayTime(day,
				dayTime);
		System.out.println("list size: " + list.size());

		for (VMTaskSchedule l : list) {
			System.out.println("processing " + l.toString());
			VMTask task = new VMTask();
			task.setHost(l.getHost());
			task.setTask(l.getTask());
			processAsync(task);
		}
	}
}
