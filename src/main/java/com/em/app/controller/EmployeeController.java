package com.em.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.em.app.dto.EmployeeDto;
import com.em.app.impl.EmployeeInterface;
import com.em.app.model.EmployeeModel;

@RestController
//@Controller
@SessionAttributes("name")
public class EmployeeController {

    @Autowired
    EmployeeModel employeeLogin;
    @Autowired
    EmployeeInterface employeeInterface;

    @GetMapping(value = "/employee/login")
    public String showLoginPage(ModelMap model) {
        return "index";
    }

    @PostMapping(value = "/employee/login")
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {
        boolean isValidUser = employeeLogin.validateUser(name, password);

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "index";
        }

        model.put("name", name);
        model.put("password", password);

        return "employee";
    }

    /*
     * @PostMapping(value = "/add/employee/detail") public String addEmployee(Model
     * model, @Validated EmployeeDto dto) { employeeInterface.save(dto); return
     * "employee"; }
     */

    @PostMapping(value = "/add/employee/detail",consumes = {"application/json"})
    public EmployeeDto addEmployee(@RequestBody EmployeeDto dto) {
        employeeInterface.save(dto);
        return dto;
    }

    @GetMapping(path ="/get/employee/detail")
    public List<EmployeeDto> getEmployee() {
        return employeeInterface.findAll();
    }

    @RequestMapping(path ="/get/employee/detail/{eid}")
    public Optional<EmployeeDto> getEmployee(@PathVariable("eid") long eid){
        return employeeInterface.findById(eid);
    }
    @DeleteMapping(value ="/delete/employee/detail/{eid}")
    public String deleteEmployee(@PathVariable long eid) {
        EmployeeDto d = employeeInterface.getOne(eid);
        employeeInterface.delete(d);
        return "deleted";


    }

    @PutMapping(value="/put/employee/detail",consumes = {"application/json"})
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto dto) {
        employeeInterface.save(dto);
        return dto;

    }

    /*
     * @GetMapping(value = "/get/employee/detail") public String getEmployee(Model
     * model, @RequestParam long eid) { return "employee"; }
     *
     * @PutMapping(value = "/put/employe/detail") public String putEmployee(Model
     * model, @RequestBody EmployeeDto dto) { return "employee"; }
     *
     * @DeleteMapping(value = "/delete/employee/detail") public String
     * deleteEmployee(Model model, @RequestParam long eid) { return "employee"; }
     */

    /*
     * @RequestMapping("/employeedto/{eid}")
     *
     * @ResponseBody public String getEmployee(@PathVariable("eid") long eid) {
     * return employeeInterface.findById(eid).toString();
     *
     * }
     */


}
