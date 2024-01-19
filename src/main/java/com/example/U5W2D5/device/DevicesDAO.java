package com.example.U5W2D5.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DevicesDAO extends JpaRepository<Device, UUID> {

}
