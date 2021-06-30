package com.nbu.logistics.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nbu.logistics.dto.OfficeDto;
import com.nbu.logistics.dto.search.SearchOfficeDto;
import com.nbu.logistics.entity.Company;
import com.nbu.logistics.entity.Office;
import com.nbu.logistics.exceptions.DoesNotExistsException;
import com.nbu.logistics.exceptions.InvalidInputException;
import com.nbu.logistics.repository.CompanyRepository;
import com.nbu.logistics.repository.OfficeRepository;
import com.nbu.logistics.repository.UserRepository;
import com.nbu.logistics.tools.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OfficeService {

	@Autowired
	private OfficeRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyRepository companyRepository;

	public OfficeDto createOffice(OfficeDto officeDto) {
		validateOfficeDto(officeDto);
		Office office = repository.save(ObjectConverter.convertObject(officeDto, Office.class));
		Company c = companyRepository.findById(officeDto.getCompany().getId()).orElse(null);
		office.setCompany(c);
		return ObjectConverter.convertObject(office, OfficeDto.class);
	}

	public OfficeDto updateOffice(OfficeDto officeDto, Long id) {
		Optional<Office> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("Office does not exist.");
		}
		validateOfficeDto(officeDto);
		existing.get().setCity(officeDto.getCity());
		existing.get().setAddress(officeDto.getAddress());
		existing.get().setCompany(ObjectConverter.convertObject(officeDto.getCompany(), Company.class));
		existing.get().setUpdatedTs(Instant.now());

		return ObjectConverter.convertObject(repository.save(existing.get()), OfficeDto.class);
	}

	public void deleteOffice(Long id) {
		Optional<Office> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("Office does not exist.");
		}
		userRepository.deleteAllByOfficeId(id);
		repository.delete(existing.get());
	}

	public OfficeDto getOffice(Long id) {
		Optional<Office> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException("Office does not exist.");
		}
		return ObjectConverter.convertObject(existing.get(), OfficeDto.class);
	}

	public List<OfficeDto> listOffices() {
		return ObjectConverter.convertList(repository.findAll(), OfficeDto.class);
	}

	public List<OfficeDto> searchOffices(SearchOfficeDto searchOfficeDto) {
		return ObjectConverter.convertList(repository.findAllByCity(searchOfficeDto.getCity()), OfficeDto.class);
	}

	private void validateOfficeDto(OfficeDto officeDto) {
		if (!companyRepository.existsById(officeDto.getCompany().getId())) {
			throw new DoesNotExistsException("Company does not exist.");
		}
		if (repository.existsByCityAndAddress(officeDto.getCity(), officeDto.getAddress())) {
			throw new InvalidInputException("There's already office on that address.");
		}
	}

}
