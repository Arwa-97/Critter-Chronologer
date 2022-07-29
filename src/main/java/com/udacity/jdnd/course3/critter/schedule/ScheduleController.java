package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule request = ScheduleDTO.convertScheduleDTOToSchedule(scheduleDTO);
        return ScheduleDTO.convertScheduleToScheduleDTO(this.scheduleService.save(request));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return ScheduleDTO.convertScheduleToScheduleDTO(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return ScheduleDTO.convertScheduleToScheduleDTO(scheduleService.findByPetId(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return ScheduleDTO.convertScheduleToScheduleDTO(scheduleService.findByEmployeeId(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return ScheduleDTO.convertScheduleToScheduleDTO(scheduleService.findByCustomerId(customerId));
    }
}
