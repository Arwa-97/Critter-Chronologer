package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if(schedule.getPets() != null) {
            List<Long> petIds = new ArrayList<>();
            for (int i = 0; i < schedule.getPets().size(); i++) {
                petIds.add(schedule.getPets().get(i).getId());
            }
            scheduleDTO.setPetIds(petIds);
        }
        if(schedule.getEmployees() != null) {
            List<Long> employeeIds = new ArrayList<>();
            for (int i = 0; i < schedule.getEmployees().size(); i++) {
                employeeIds.add(schedule.getEmployees().get(i).getId());
            }
            scheduleDTO.setEmployeeIds(employeeIds);
        }
        return scheduleDTO;
    }
    public static List<ScheduleDTO> convertScheduleToScheduleDTO(List<Schedule> schedules){
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            ScheduleDTO scheduleDTO= new ScheduleDTO();
            BeanUtils.copyProperties(schedules.get(i), scheduleDTO);
            List<Long> petIds = new ArrayList<>();

            for (int j = 0; j < schedules.get(i).getPets().size(); j++) {
                petIds.add(schedules.get(i).getPets().get(j).getId());
            }
            scheduleDTO.setPetIds(petIds);

            List<Long> employeeIds = new ArrayList<>();

            for (int k = 0; k < schedules.get(i).getEmployees().size(); k++) {
                employeeIds.add(schedules.get(i).getEmployees().get(k).getId());
            }
            scheduleDTO.setEmployeeIds(employeeIds);
            scheduleDTOList.add(scheduleDTO);
        }
        return scheduleDTOList;
    }
    public static Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if(scheduleDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>(scheduleDTO.getPetIds().size());
            for (int i = 0; i < scheduleDTO.getPetIds().size(); i++) {
                Pet newPet = new Pet();
                newPet.setId(scheduleDTO.getPetIds().get(i));
                pets.add(newPet);
            }
            schedule.setPets(pets);
        }
        if(scheduleDTO.getEmployeeIds() != null) {
            List<Employee> employees = new ArrayList<>(scheduleDTO.getEmployeeIds().size());
            for (int i = 0; i < scheduleDTO.getEmployeeIds().size(); i++) {
                Employee emp = new Employee();
                emp.setId(scheduleDTO.getEmployeeIds().get(i));
                employees.add(emp);
            }
            schedule.setEmployees(employees);
        }
        return schedule;
    }
}
