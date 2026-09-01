package com.factory.logistics.shipment.dto.CreateShipment;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateShipmentRequest {

    @NotBlank(message = "Product code cannot be blank")
    @Size(max = 50, message = "Product code cannot exceed 50 characters")
    private String productCode;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Destination city cannot be blank")
    @Size(max = 100, message = "Destination city cannot exceed 100 characters")
    private String destinationCity;

    @NotNull(message = "Destination latitude cannot be null")
    private BigDecimal destinationLatitude;

    @NotNull(message = "Destination longitude cannot be null")
    private BigDecimal destinationLongitude;

    @NotNull(message = "Dispatch date cannot be null")
    @FutureOrPresent(message = "Dispatch date must be today or in the future")
    private LocalDate dispatchDate;
}
