package com.flexpag.paymentscheduler.entity;

import com.flexpag.paymentscheduler.dto.RequestScheduling;
import com.flexpag.paymentscheduler.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "scheduling")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Scheduling {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    UUID id;

    @Enumerated(EnumType.STRING)
    Status status;

    String date;

    int amount;

    public Scheduling(RequestScheduling data){
        this.status = Status.pending;
        this.date = data.getDate();
        this.amount = data.getAmount();
    }

}
