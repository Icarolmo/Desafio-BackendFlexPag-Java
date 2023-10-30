package com.flexpag.paymentscheduler.dto;

import com.flexpag.paymentscheduler.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}
