package com.address.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "http://localhost:8084",value = "Employee-client")
public interface EmployeeClient {

}
