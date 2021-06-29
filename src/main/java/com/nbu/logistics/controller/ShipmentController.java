package com.nbu.logistics.controller;

import com.nbu.logistics.dto.ShipmentDto;
import com.nbu.logistics.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {
    @Autowired
    ShipmentService service;

    @PostMapping
    public ShipmentDto createShipment(@RequestBody ShipmentDto shipmentDto) {
        return service.createOnlineShipment(shipmentDto);
    }

    @GetMapping
    public List<ShipmentDto> listShipments() {
        return service.listOfShipments();
    }

    @GetMapping("/{id}")
    public ShipmentDto getShipment(@PathVariable Long id) {
        return service.getShipment(id);
    }

    @PutMapping("{id}")
    public ShipmentDto updateShipment(@PathVariable Long id, @RequestBody ShipmentDto shipmentDto) {
        return service.updateShipment(id, shipmentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteShipment(@PathVariable Long id) {
        service.deleteShipment(id);
    }

    @PatchMapping("/register/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')" + "&& hasRole('ROLE_OFFICE')")
    public void registerShipment(@PathVariable Long id) {
        service.registerShipment(id);
    }
}
