package com.address.controller;

import com.address.model.dto.AddressDto;
import com.address.service.AddressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveAddress(@RequestBody AddressDto addressDto) {
		boolean saved = addressService.saveAddress(addressDto);
		return saved ? ResponseEntity.ok("Address saved successfully")
				: ResponseEntity.status(500).body("Failed to save address");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
		boolean deleted = addressService.deleteAddress(id);
		return deleted ? ResponseEntity.ok("Address deleted successfully")
				: ResponseEntity.status(404).body("Address not found");
	}

	@GetMapping("/employee/{empId}/hi")
	public ResponseEntity<List<AddressDto>> getAddressesByEmpId(@PathVariable Long empId) {
		List<AddressDto> addresses = addressService.getAllAddressesByEmpId(empId);
		return ResponseEntity.ok(addresses);
	}

	@GetMapping("/employee/{empId}")
	public ResponseEntity<?> hello(@PathVariable Long empId) {
		boolean exists = addressService.isEmployeeExists(empId);

		if (exists) {
			return ResponseEntity.ok("Employee with ID " + empId + " exists.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + empId + " not found.");
		}
	}

}