package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.dto.SchedulingDTO;
import com.flexpag.paymentscheduler.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

    @Autowired
    SchedulingRepository repository;

    public SchedulingDTO getAllScheduling(){
        return  (SchedulingDTO) repository.findAll();
    }

}
