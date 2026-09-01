package com.factory.logistics.shipment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.factory.logistics.shipment.dto.CreateShipment.CreateShipmentRequest;
import com.factory.logistics.shipment.dto.CreateShipment.CreateShipmentResponse;
import com.factory.logistics.shipment.entity.Shipment;
import com.factory.logistics.shipment.enums.RiskLevel;
import com.factory.logistics.shipment.repository.ShipmentRepository;
import com.factory.logistics.weather.service.WeatherRiskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final WeatherRiskService weatherRiskService;

    public CreateShipmentResponse createShipment(CreateShipmentRequest request) {
        RiskLevel riskLevel = weatherRiskService.getRiskLevel(request.getDestinationLatitude(), request.getDestinationLongitude(), request.getDispatchDate());

        Shipment shipment = Shipment.builder()
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .destinationCity(request.getDestinationCity())
                .destinationLatitude(request.getDestinationLatitude())
                .destinationLongitude(request.getDestinationLongitude())
                .dispatchDate(request.getDispatchDate())
                .riskLevel(riskLevel)
                .build();

        Shipment savedShipment = shipmentRepository.save(shipment);
        return CreateShipmentResponse.fromEntity(savedShipment);
    }

    public List<CreateShipmentResponse> getAllShipments() {
        return shipmentRepository.findAll().stream().map(CreateShipmentResponse::fromEntity).toList();
    }

    public CreateShipmentResponse getShipmentById(UUID shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new com.factory.logistics.exception.ResourceNotFoundException("Shipment not found with id: " + shipmentId));
        return CreateShipmentResponse.fromEntity(shipment);
    }
}
