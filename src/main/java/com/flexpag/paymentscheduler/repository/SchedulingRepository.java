package com.flexpag.paymentscheduler.repository;

import com.flexpag.paymentscheduler.dto.SchedulingDTO;
import com.flexpag.paymentscheduler.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

    Optional<Scheduling> findById(UUID id);

    List<Scheduling> findAll();
}
