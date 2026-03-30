package com.address.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.address.model.dto.AddressDto;
import com.address.model.entity.Address;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate; // Step 2: Inject here

	// Step 2: method to check employee existence
	public boolean isEmployeeExists(Long empId) {
		String url = "http://localhost:8084/employees/" + empId; // Service 2 URL
		try {
			restTemplate.getForObject(url, Object.class); // Call Service 2
			return true;
		} catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
			return false;
		}
	}

	@Override
	public boolean saveAddress(AddressDto addressDto) {
		Address address = new Address();
		address.setEmpId(addressDto.getEmpId());
		address.setStreet(addressDto.getStreet());
		address.setPinCode(addressDto.getPinCode());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setAddressType(addressDto.getAddressType());

		Address saved = addressRepository.save(address);
		return saved.getId() != null;
	}

	@Override
	public boolean deleteAddress(Long id) {
		if (!addressRepository.existsById(id)) {
			return false; // nothing to delete
		}
		addressRepository.deleteById(id);
		return true;
	}

	@Override
	public List<AddressDto> getAllAddressesByEmpId(Long empId) {
		List<Address> addresses = addressRepository.findAllByEmpId(empId);
		return addresses.stream().map(addr -> modelMapper.map(addr, AddressDto.class)).toList();
	}
}