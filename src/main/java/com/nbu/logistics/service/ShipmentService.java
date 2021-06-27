package com.nbu.logistics.service;

import com.nbu.logistics.dto.ShipmentDto;
import com.nbu.logistics.dto.UserDto;
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

    public ShipmentDto createOnlineShipment(ShipmentDto shipmentDto) {
        Shipment shipment = ObjectConverter.convertObject(shipmentDto, Shipment.class);
        User receiver = userRepository.findByUsername(shipmentDto.getTarget()).get();
        shipment.setReceiver(receiver);
        shipment.setSender(userRepository.findByUsername(AuthenticationUtils.getAuthenticatedUsername()).get());
        shipmentRepository.save(shipment);

        return ObjectConverter.convertObject(shipment, ShipmentDto.class);
    }

    public List<ShipmentDto> listOfShipments() {
        return ObjectConverter.convertList(shipmentRepository.findAll(), ShipmentDto.class);
    }

    public ShipmentDto getShipment(Long id) {
        Optional<Shipment> existing = shipmentRepository.findById(id);

        if (!existing.isPresent()) {
            throw new DoesNotExistsException("Shipment does not exist.");
        }
        return ObjectConverter.convertObject(existing.get(), ShipmentDto.class);
    }

    public ShipmentDto updateShipment(Long id, ShipmentDto shipmentDto) {
        Optional<Shipment> existing = shipmentRepository.findById(id);

        if (!existing.isPresent()) {
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

        if (!existing.isPresent()) {
            throw new DoesNotExistsException("Shipment does not exist.");
        }

        shipmentRepository.delete(existing.get());
    }
}