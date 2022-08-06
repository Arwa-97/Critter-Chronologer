package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Repository.User.EmployeeRepository;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        Employee response = employeeRepository.save(employee);
        return response;
    }
    public Employee findById(Long id) {
        Employee response = employeeRepository.findById(id).get();
        return response;
    }
    public void save(Set<DayOfWeek> daysAvailable, Long id){
        Optional<Employee> employee = this.employeeRepository.findById(id);
        employee.get().setDaysAvailable(daysAvailable);
        this.employeeRepository.save(employee.get());
    }
    public List<Employee> checkAvailability(LocalDate date, Set<EmployeeSkill> skills){
        List<Employee> response = this.employeeRepository.findAllByDaysAvailable(date.getDayOfWeek());
         return response.stream().filter(emp -> emp.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}
