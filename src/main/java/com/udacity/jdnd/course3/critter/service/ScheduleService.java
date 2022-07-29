package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Repository.User.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.schedule.ScheduleRepository;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScheduleService {
    private ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Schedule save(Schedule schedule) {
        Schedule response = this.repository.save(schedule);
        for (Employee emp : response.getEmployees()) {
            this.repository.updateEmployeeSchedule(response.getId(), emp.getEmployeeId());
        }
        for (Pet pet : response.getPets()) {
            this.repository.updatePetSchedule(response.getId(), pet.getId());
        }
        return response;
    }
    public List<Schedule> getAllSchedules() {
        List<Schedule> response = (List<Schedule>) repository.findAll();
        return response;
    }
    public List<Schedule> findByPetId(Long petId){
       return repository.findSchedulesByPetId(petId);
    }
    public List<Schedule> findByCustomerId(Long customerId){
       return repository.findSchedulesByCustomerId(customerId);
    }
    public List<Schedule> findByEmployeeId(Long employeeId){
       return repository.findSchedulesByEmployeeId(employeeId);
    }
}
