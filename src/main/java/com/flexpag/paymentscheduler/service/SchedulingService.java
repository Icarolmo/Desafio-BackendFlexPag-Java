package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.dto.RequestScheduling;
import com.flexpag.paymentscheduler.dto.SchedulingDTO;
import com.flexpag.paymentscheduler.entity.Scheduling;
import com.flexpag.paymentscheduler.enumeration.Status;
import com.flexpag.paymentscheduler.repository.SchedulingRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

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
        if(allSchedulings.isEmpty()){
            throw new EntityNotFoundException();
        }
        return allSchedulings;
    }

    public SchedulingDTO getSchedulingById(UUID id){
        Optional<Scheduling> transferData = repository.findById(id);
        if(transferData.isEmpty()){
            throw new EntityNotFoundException();
        } else {
            return new SchedulingDTO(
                    transferData.get().getId(),
                    transferData.get().getStatus(),
                    transferData.get().getDate(),
                    transferData.get().getAmount()
            );
        }
    }

    public SchedulingDTO createNewScheduling(RequestScheduling data) throws ParseException, DataFormatException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date requestDate = format.parse(data.getDate());
        Date dateNow = new Date();
        if(dateNow.compareTo(requestDate) < 0){
            return new SchedulingDTO(repository.save(new Scheduling(data)));
        }
        throw new DataFormatException("Error: data para agendamento igual ou menor a data de hoje :" + dateNow.toString());
    }

    public SchedulingDTO updateScheduling(UUID id, RequestScheduling data) throws ParseException, DataFormatException {
        Scheduling alteringScheduling = repository.getReferenceById(id);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date requestDate = format.parse(data.getDate());
        Date dateNow = new Date();
        if(dateNow.compareTo(requestDate) >= 0){
            throw new DataFormatException("Error: data para agendamento igual ou menor a data de hoje :" + dateNow.toString());
        }
        if(alteringScheduling.getStatus() == Status.paid){
            throw new DataFormatException("Error: pagamento já efetivado. Status: " + alteringScheduling.getStatus());
        }
        alteringScheduling.setDate(data.getDate());
        alteringScheduling.setAmount(data.getAmount());

        entityManager.merge(alteringScheduling);

        return new SchedulingDTO(alteringScheduling);
    }

    public void deleteScheduling(UUID id) throws DataFormatException {
        Scheduling deletingScheduling = repository.getReferenceById(id);
        if(deletingScheduling.getStatus() == Status.paid){
            throw new DataFormatException("Error: pagamento já efetivado. Status: " + deletingScheduling.getStatus());
        }
        repository.deleteById(id);
    }

    public SchedulingDTO paymentingSchedulingTest(UUID id) {
        Scheduling schedulingTest = repository.getReferenceById(id);
        schedulingTest.setStatus(Status.paid);

        entityManager.merge(schedulingTest);
        return new SchedulingDTO(schedulingTest);
    }


}
