package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Repository.Pet.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.User.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.User.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.schedule.ScheduleRepository;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ScheduleService {
    private ScheduleRepository repository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private PetRepository petRepository;

    public ScheduleService(ScheduleRepository repository,
                           EmployeeRepository employeeRepository,
                           PetRepository petRepository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Schedule save(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = (List<Employee>) this.employeeRepository.findAllById(employeeIds);
        List<Pet> pets = (List<Pet>) this.petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        Schedule response = this.repository.save(schedule);
        return response;
    }
    public List<Schedule> getAllSchedules() {
        List<Schedule> response = (List<Schedule>) repository.findAll();
        return response;
    }
    public List<Schedule> findByPetId(Long petId){
        Pet pet = this.petRepository.findById(petId).get();
        List<Schedule> response = repository.findSchedulesByPets(pet);
       return response;
    }
    public List<Schedule> findByCustomerId(Long customerId){
        Customer customer = this.customerRepository.findById(customerId).get();
        List<Schedule> response = repository.findAllByPetsIn(customer.getPets());
        return response;
    }
    public List<Schedule> findByEmployeeId(Long employeeId){
        Employee employee = this.employeeRepository.findById(employeeId).get();
        List<Schedule> response = repository.findSchedulesByEmployeesContains(employee);
        return response;
    }
}
