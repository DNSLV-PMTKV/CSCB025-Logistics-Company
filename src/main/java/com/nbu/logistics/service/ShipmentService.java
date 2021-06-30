package com.nbu.logistics.service;

import com.nbu.logistics.dto.CreateShipmentDto;
import com.nbu.logistics.dto.ShipmentDto;
import com.nbu.logistics.entity.Shipment;
import com.nbu.logistics.entity.User;
import com.nbu.logistics.exceptions.DoesNotExistsException;
import com.nbu.logistics.repository.ShipmentRepository;
import com.nbu.logistics.repository.UserRepository;
import com.nbu.logistics.tools.AuthenticationUtils;
import com.nbu.logistics.tools.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    public ShipmentDto createShipment(ShipmentDto shipmentDto) {
        Shipment shipment = ObjectConverter.convertObject(shipmentDto, Shipment.class);
        shipmentRepository.save(ObjectConverter.convertObject(shipmentDto, Shipment.class));
        return ObjectConverter.convertObject(shipment, ShipmentDto.class);
    }

    public ShipmentDto createOnlineShipment(CreateShipmentDto shipmentDto) {
        Shipment shipment = ObjectConverter.convertObject(shipmentDto, Shipment.class);
        User target = userRepository.findByUsername(shipmentDto.getTarget()).get();
        shipment.setTarget(target);
        shipment.setSender(userRepository.findByUsername(AuthenticationUtils.getAuthenticatedUsername()).get());
        shipmentRepository.save(shipment);

        return ObjectConverter.convertObject(shipment, ShipmentDto.class);
    }

    public List<ShipmentDto> listOfShipments() {
        return ObjectConverter.convertList(shipmentRepository.findAll(), ShipmentDto.class);
    }

    public ShipmentDto getShipment(Long id) {
        Optional<Shipment> existing = shipmentRepository.findById(id);

        if (existing.isEmpty()) {
            throw new DoesNotExistsException("Shipment does not exist.");
        }
        return ObjectConverter.convertObject(existing.get(), ShipmentDto.class);
    }

    public ShipmentDto updateShipment(Long id, ShipmentDto shipmentDto) {
        Optional<Shipment> existing = shipmentRepository.findById(id);

        if (existing.isEmpty()) {
            throw new DoesNotExistsException("Shipment does not exist");
        }

        //TODO UPDATE
        existing.get().setAddress(shipmentDto.getAddress());
        existing.get().setWeight(shipmentDto.getWeight());
        existing.get().setUpdatedTs(Instant.now());

        return ObjectConverter.convertObject(existing.get(), ShipmentDto.class);
    }

    public void deleteShipment(Long id) {
        Optional<Shipment> existing = shipmentRepository.findById(id);

        if (existing.isEmpty()) {
            throw new DoesNotExistsException("Shipment does not exist.");
        }

        shipmentRepository.delete(existing.get());
    }

    public void registerShipment(Long id) {
        Optional<Shipment> existing = shipmentRepository.findById(id);

        if (existing.isEmpty()) {
            throw new DoesNotExistsException("Shipment does not exist.");
        }

        existing.get().setEmployee(userRepository.findByUsername(AuthenticationUtils.getAuthenticatedUsername()).get());

        existing.get().setRegisteredStatus(true);
    }

    public List<ShipmentDto> getListOfRegisteredShipments() {
        return ObjectConverter.convertList(shipmentRepository.findByRegisteredStatusTrue(), ShipmentDto.class);
    }

    public List<ShipmentDto> getListOfRegisteredShipmentsByUser(String username) {
        User employee = userRepository.findByUsername(username).get();
        System.out.println(employee.getUsername());
        return ObjectConverter.convertList(shipmentRepository.findByRegisteredStatusTrueAndEmployee(employee), ShipmentDto.class);
    }

    public List<ShipmentDto> getListOfRegisteredShipmentsNotDelivered() {
        return ObjectConverter.convertList(shipmentRepository.findByRegisteredStatusTrueAndDeliveredStatusFalse(), ShipmentDto.class);
    }

    public List<ShipmentDto> getListOfRegisteredShipmentsBySender(String username) {
        User sender = userRepository.findByUsername(username).get();
        return ObjectConverter.convertList(shipmentRepository.findByRegisteredStatusTrueAndSender(sender), ShipmentDto.class);
    }

    public List<ShipmentDto> getListOfRegisteredShipmentsByReceiver(String username) {
        User receiver = userRepository.findByUsername(username).get();
        return ObjectConverter.convertList(shipmentRepository.findByRegisteredStatusTrueAndTarget(receiver), ShipmentDto.class);
    }
}