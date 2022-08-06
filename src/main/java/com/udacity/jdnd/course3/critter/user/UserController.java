package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private CustomerService customerService;
    private EmployeeService employeeService;

    public UserController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = CustomerDTO.convertCustomerDTOToCustomer(customerDTO);
        CustomerDTO response = CustomerDTO.convertCustomerToCustomerDTO(customerService.save(customer, customerDTO.getPetIds()));
        return response;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return CustomerDTO.convertCustomerToCustomerDTO(customerService.findAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return CustomerDTO.convertCustomerToCustomerDTO(customerService.findOwnerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = EmployeeDTO.convertEmployeeDTOToEmployee(employeeDTO);
        EmployeeDTO response = EmployeeDTO.convertEmployeeToEmployeeDTO(employeeService.save(employee));
        return response;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeDTO.convertEmployeeToEmployeeDTO(employeeService.findById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.save(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
         List<Employee> emp = this.employeeService.checkAvailability(employeeDTO.getDate(), employeeDTO.getSkills());
         return EmployeeDTO.convertEmployeeToEmployeeDTO(emp);
    }

}
