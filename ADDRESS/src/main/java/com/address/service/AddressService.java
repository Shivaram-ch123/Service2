package com.address.service;

import com.address.model.dto.AddressDto;
import com.address.model.dto.AddressRequest;

import java.util.List;

public interface AddressService {

	boolean saveAddress(AddressDto addressDto);

//	List<AddressDto> updateAddress();

//	void deleteAddress(Long id);
	boolean deleteAddress(Long id);

	List<AddressDto> getAllAddressesByEmpId(Long empId);
	boolean isEmployeeExists(Long empId);
}
