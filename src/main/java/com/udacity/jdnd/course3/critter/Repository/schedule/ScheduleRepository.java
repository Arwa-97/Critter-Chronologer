package com.udacity.jdnd.course3.critter.Repository.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    @Query(value = "SELECT s.*, e.employee_id, e.name, " +
            "p.pet_id, p.birth_date, p.name, p.notes, p.type, p.customer_id " +
            "FROM Schedule s JOIN Employee e " +
            "ON s.schedule_id = e.schedule_id JOIN pet p ON " +
            "p.schedule_id = s.schedule_id",
    nativeQuery = true)
    List<Schedule> findAllSchedules();

    @Query(value = "SELECT s.*, e.employee_id, e.name, " +
            "p.pet_id, p.birth_date, p.name, p.notes, p.type, p.customer_id " +
            "FROM Schedule s JOIN Employee e " +
            "ON s.schedule_id = e.schedule_id JOIN pet p ON " +
            "p.schedule_id = s.schedule_id WHERE p.pet_id = :petId",
    nativeQuery = true)
    List<Schedule> findSchedulesByPetId(Long petId);

    @Query(value = "SELECT s.*, e.employee_id, e.name, " +
            "p.pet_id, p.birth_date, p.name, p.notes, p.type, p.customer_id " +
            "FROM Schedule s JOIN Employee e " +
            "ON s.schedule_id = e.schedule_id JOIN pet p ON " +
            "p.schedule_id = s.schedule_id WHERE p.customer_id = :customerId",
    nativeQuery = true)
    List<Schedule> findSchedulesByCustomerId(Long customerId);

    @Query(value = "SELECT s.*, e.employee_id, e.name, " +
            "p.pet_id, p.birth_date, p.name, p.notes, p.type, p.customer_id " +
            "FROM Schedule s JOIN Employee e " +
            "ON s.schedule_id = e.schedule_id JOIN pet p ON " +
            "p.schedule_id = s.schedule_id WHERE e.employee_id = :employeeId",
    nativeQuery = true)
    List<Schedule> findSchedulesByEmployeeId(Long employeeId);

    @Modifying
    @Query(value = "UPDATE employee SET schedule_id = :id WHERE " +
            "employee_id = :employeeId",
    nativeQuery = true)
    void updateEmployeeSchedule(Long id, Long employeeId);
    @Modifying
    @Query(value = "UPDATE pet SET schedule_id = :id WHERE " +
            "pet_id = :petId",
    nativeQuery = true)
    void updatePetSchedule(Long id, Long petId);
}
