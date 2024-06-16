package com.uep.wap.service;
import com.uep.wap.interfaces.ICustomerService;
import com.uep.wap.dto.CustomerDto;
import com.uep.wap.mappers.CustomerMapper;
import com.uep.wap.model.Customer;
import com.uep.wap.repository.CustomerRepository;
import com.uep.wap.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService extends ConstantUtil implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createCustomer(CustomerDto customerDto) {
            Customer customer = customerMapper.customerDtoToCustomer(customerDto);
            customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerDto.getId()));
        // Update customer properties based on customerDto
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhoneNumber(customerDto.getPhone());
        // Save the updated customer
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::customerToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
        return customerMapper.customerToCustomerDto(customer);
    }
}
