package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.dto.RequestScheduling;
import com.flexpag.paymentscheduler.dto.SchedulingDTO;
import com.flexpag.paymentscheduler.entity.Scheduling;
import com.flexpag.paymentscheduler.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository repository;

    @Autowired
    private EntityManager entityManager;

    public List<SchedulingDTO> getAllScheduling(){
        List<SchedulingDTO> allSchedulings = new ArrayList<>();
        for(Scheduling databaseScheduling : repository.findAll()){
            allSchedulings.add(new SchedulingDTO(databaseScheduling));
        }
        return allSchedulings;
    }

    public SchedulingDTO getSchedulingById(UUID id){
        Optional<Scheduling> transferData = repository.findById(id);
        if(transferData.isPresent()){
            return new SchedulingDTO(
                    transferData.get().getId(),
                    transferData.get().getStatus(),
                    transferData.get().getDate(),
                    transferData.get().getAmount());
        } else {
            return null;
        }
    }

    public SchedulingDTO createNewScheduling(RequestScheduling data){
        return new SchedulingDTO(repository.save(new Scheduling(data)));
    }

    public SchedulingDTO updateScheduling(UUID id, RequestScheduling data){
        Scheduling alteringScheduling = repository.getReferenceById(id);

        alteringScheduling.setDate(data.getDate());
        alteringScheduling.setAmount(data.getAmount());

        entityManager.merge(alteringScheduling);

        return new SchedulingDTO(alteringScheduling);
    }

    public void deleteScheduling(UUID id){
        repository.deleteById(id);
    }


}
