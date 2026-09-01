package com.factory.logistics.shipment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.factory.logistics.shipment.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

}
