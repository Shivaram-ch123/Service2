package com.address.service.impl;

import java.util.*;
import java.util.List;
import com.address.model.dto.*;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.model.dto.AddressDto;
import com.address.model.dto.AddressRequest;
import com.address.model.dto.AddressRequestDto;
import com.address.model.entity.Address;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AddressServiceImpl implements AddressService {

	Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AddressDto> saveAddress(AddressRequest addressRequest) {

		List<Address> listToSave = this.saveOrUpdateAddressRequest(addressRequest);

		List<Address> savedAddress = addressRepository.saveAll(listToSave);

		return savedAddress.stream().map(addr -> modelMapper.map(addr, AddressDto.class)).toList();
	}

//	@Override
//	public List<AddressDto> updateAddress(AddressRequest addressRequest) {
//		// TODO: check if employee exists
//
//		List<Address> addressByEmpId = addressRepository.findAllByEmpId(addressRequest.getEmpId());
//
//		if (addressByEmpId.isEmpty()) {
//			throw new RuntimeException("No address found for employee");
//		}
//		
//		List<Address> listToUpdate = this.saveOrUpdateAddressRequest(addressRequest);
//		
//	}

	@Override
	public AddressDto getSingleAddress(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressDto> getAllAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAddress(Long id) {
		// TODO Auto-generated method stub

	}

	private List<Address> saveOrUpdateAddressRequest(AddressRequest addressRequest) {
		List<Address> listToSave = new ArrayList<>();

		for (AddressRequestDto dto : addressRequest.getAddressRequestDtoList()) {

			Address address = new Address();
			address.setId(dto.getId()!=null ? dto.getId():null);
			address.setStreet(dto.getStreet());
			address.setCity(dto.getCity());
			address.setCountry(dto.getCountry());

			// ✅ FIX: convert int → String
			address.setPinCode(String.valueOf(dto.getPinCode()));

			address.setAddressType(dto.getAddressType());

			// ✅ FIX: empId from parent object
			address.setEmpId(addressRequest.getEmpId());

			listToSave.add(address);
		}
		return listToSave;
	}

	@Override
	public List<AddressDto> updateAddress(AddressRequest addressRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
