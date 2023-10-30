package com.flexpag.paymentscheduler.controller;

import com.flexpag.paymentscheduler.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    SchedulingService service;


    @GetMapping @ExceptionHandler
    public ResponseEntity getAllScheduling(){
        return ResponseEntity.ok().body(service.getAllScheduling());
    }
}
