package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.dto.RequestScheduling;
import com.flexpag.paymentscheduler.service.SchedulingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    SchedulingService service;

    @GetMapping
    public ResponseEntity getAllScheduling(){
        log.info("Chamando servico de consulta agendamentos: getAllScheduling");
        return ResponseEntity.ok().body(service.getAllScheduling());
    }

    @GetMapping(params = "id")
    public ResponseEntity getSchedulingById(@RequestParam(name = "id") UUID id){
        log.info("Chamando servico de consulta agendamentos por id: getSchedulingById");
        return ResponseEntity.ok().body(service.getSchedulingById(id));
    }

    @PostMapping
    public ResponseEntity createNewScheduling(@RequestBody @Validated RequestScheduling data){
        log.info("Chamando servico de criação de agendamentos: createNewScheduling");
        return ResponseEntity.ok().body(service.createNewScheduling(data));
    }

    @PutMapping
    public ResponseEntity updateScheduling(@RequestParam(name = "id") UUID id, @RequestBody @Validated RequestScheduling data){
        log.info("Chamando servico de alteração de agendamentos: updateScheduling");
        return ResponseEntity.ok().body(service.updateScheduling(id, data));
    }

    @DeleteMapping
    public ResponseEntity deleteScheduling(@RequestParam(name = "id") UUID id){
        log.info("Chamando servico de exclusão de agendamentos: deleteScheduling");
        service.deleteScheduling(id);
        return ResponseEntity.ok().build();
    }

}
