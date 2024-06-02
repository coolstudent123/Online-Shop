package com.uep.wap.controller;

import com.uep.wap.model.Employee;
import com.uep.wap.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
//
//    @GetMapping("/")
//    public String viewHomePage(Model model) {
//        model.addAttribute("allemplist", employeeServiceImpl.getAllEmployee());
//        return "index";
//    }

//    @GetMapping("/addnew")
//    public String addNewEmployee(Model model) {
//        Employee employee = new Employee();
//        model.addAttribute("employee", employee);
//        return "nowypracownik";
//    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeServiceImpl.save(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeServiceImpl.getById(id);
        model.addAttribute("employee", employee);
        return "aktualizacja";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        employeeServiceImpl.deleteViaId(id);
        return "redirect:/";

    }
}
