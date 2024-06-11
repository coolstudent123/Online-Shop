package com.uep.wap.mappers;

import com.uep.wap.dto.CustomerDto;
import com.uep.wap.model.Customer;

import java.util.List;

public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customer);

    List<CustomerDto> customerListToCustomerDtoList(List<Customer> customers);
}