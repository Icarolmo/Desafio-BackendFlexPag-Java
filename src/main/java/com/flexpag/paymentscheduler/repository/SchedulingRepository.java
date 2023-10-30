package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {
}
