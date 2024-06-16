package com.uep.wap.mappers;
import com.uep.wap.model.Customer;

import org.springframework.stereotype.Component;
import com.uep.wap.dto.CustomerDto;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setPhone(customer.getPhoneNumber());

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = new Customer();
        if ( customerDto.getId() != null ) {
            customer.setId( customerDto.getId() );
        }
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhoneNumber(customerDto.getPhone());

        return customer;
    }

    @Override
    public List<CustomerDto> customerListToCustomerDtoList(List<Customer> customers) {
        if (customers == null) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<>(customers.size());
        for (Customer customer : customers) {
            list.add(customerToCustomerDto(customer));
        }

        return list;
    }
}
