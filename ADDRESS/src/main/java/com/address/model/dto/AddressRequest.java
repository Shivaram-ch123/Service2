package com.address.model.dto;

import java.util.List;

public class AddressRequest {

    private Long empId;
    private AddressRequestDto addressRequestDtoList;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public AddressRequestDto getAddressRequestDtoList() {
        return addressRequestDtoList;
    }

    public void setAddressRequestDtoList(AddressRequestDto addressRequestDtoList) {
        this.addressRequestDtoList = addressRequestDtoList;
    }
}