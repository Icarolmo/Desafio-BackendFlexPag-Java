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
    @Column(name = "id", columnDefinition = "uuid")
    UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "date")
    String date;

    @Column(name = "amount")
    int amount;

    public Scheduling(RequestScheduling data){
        this.status = Status.pending;
        this.date = data.getDate();
        this.amount = data.getAmount();
    }

}
