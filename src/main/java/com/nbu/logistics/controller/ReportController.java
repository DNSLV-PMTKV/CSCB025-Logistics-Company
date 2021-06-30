package com.nbu.logistics.controller;

import com.nbu.logistics.dto.ShipmentDto;
import com.nbu.logistics.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    ShipmentService shipmentService;

    @GetMapping("/shipment/registered")
    public List<ShipmentDto> listRegisteredShipments() {
        return shipmentService.getListOfRegisteredShipments();
    }

    @GetMapping("/shipment/registeredByUser/{username}")
    public List<ShipmentDto> listShipmentsRegisteredByUser(@PathVariable String username) {
        return shipmentService.getListOfRegisteredShipmentsByUser(username);
    }

    @GetMapping("/shipment/registeredNotDelivered")
    public List<ShipmentDto> listRegisteredShipmentsNotDelivered() {
        return shipmentService.getListOfRegisteredShipmentsNotDelivered();
    }

    @GetMapping("/shipment/sentByUser/{username}")
    public List<ShipmentDto> listRegisteredShipmentsSentByUser(@PathVariable String username) {
        return shipmentService.getListOfRegisteredShipmentsBySender(username);
    }

    @GetMapping("/shipment/receivedByUser/{username}")
    public List<ShipmentDto> listRegisteredShipmentsReceivedByUser(@PathVariable String username) {
        return shipmentService.getListOfRegisteredShipmentsByReceiver(username);
    }
}
