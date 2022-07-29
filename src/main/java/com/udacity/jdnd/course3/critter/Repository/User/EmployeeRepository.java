package com.udacity.jdnd.course3.critter.Repository.User;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "SELECT * FROM employee emp JOIN employee_skills skill ON emp.employee_id = skill.employee_id " +
            "JOIN days_available day ON emp.employee_id = day.employee_id" +
            " WHERE day.days_available = :day AND skill.skill IN :skills",
            nativeQuery = true)
    List<Employee> checkAvailability(DayOfWeek day, Set<EmployeeSkill> skills);
}
