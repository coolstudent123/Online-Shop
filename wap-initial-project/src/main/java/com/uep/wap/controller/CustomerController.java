package com.uep.wap.controller;

import com.uep.wap.dto.CustomerDto;
import com.uep.wap.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String viewAllCustomers(Model model) {
        List<CustomerDto> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers"; // Assuming you have a view named "customer-list"
    }

    @GetMapping("/customers/add")
    public String showAddCustomerForm(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customer", customerDto);
        return "customer-add"; // Assuming you have a view named "customer-add"
    }

    @PostMapping("/customers/add")
    public String addCustomer(@ModelAttribute("customer") CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Integer id, Model model) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        model.addAttribute("customer", customerDto);
        return "customer-edit"; // Assuming you have a view named "customer-edit"
    }

    @PostMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id, @ModelAttribute("customer") CustomerDto customerDto) {
        customerDto.setId(id); // Set the ID from path variable
        customerService.updateCustomer(customerDto);
        return "redirect:/customer-list";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer-list";
    }
}
