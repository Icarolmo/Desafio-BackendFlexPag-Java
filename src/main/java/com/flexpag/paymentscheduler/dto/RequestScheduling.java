package com.flexpag.paymentscheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestScheduling {
    @NonNull
    String date;
    @NonNull
    int amount;

}
