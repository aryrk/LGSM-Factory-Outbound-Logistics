package com.factory.logistics.shipment.dto.CreateShipment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.factory.logistics.shipment.entity.Shipment;
import com.factory.logistics.shipment.enums.RiskLevel;
import com.factory.logistics.shipment.enums.ShipmentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateShipmentResponse {
    private UUID id;
    private String productCode;
    private Integer quantity;
    private String destinationCity;
    private BigDecimal destinationLatitude;
    private BigDecimal destinationLongitude;
    private LocalDate dispatchDate;
    private ShipmentStatus shipmentStatus;
    private RiskLevel riskLevel;
    private LocalDateTime createdAt;

    public static CreateShipmentResponse fromEntity(Shipment shipment) {
        return CreateShipmentResponse.builder()
                .id(shipment.getId())
                .productCode(shipment.getProductCode())
                .quantity(shipment.getQuantity())
                .destinationCity(shipment.getDestinationCity())
                .destinationLatitude(shipment.getDestinationLatitude())
                .destinationLongitude(shipment.getDestinationLongitude())
                .dispatchDate(shipment.getDispatchDate())
                .shipmentStatus(shipment.getShipmentStatus())
                .riskLevel(shipment.getRiskLevel())
                .createdAt(shipment.getCreatedAt())
                .build();
    }
}
