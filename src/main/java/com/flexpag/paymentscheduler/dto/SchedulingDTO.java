package com.flexpag.paymentscheduler.dto;

import com.flexpag.paymentscheduler.entity.Scheduling;
import com.flexpag.paymentscheduler.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchedulingDTO implements Serializable {

    UUID id;

    @Enumerated(EnumType.STRING)
    Status status;

    String date;

    int amount;

    public SchedulingDTO(Scheduling newScheduling){
        this.id = newScheduling.getId();
        this.status = newScheduling.getStatus();
        this.date = newScheduling.getDate();
        this.amount = newScheduling.getAmount();
    }

}
