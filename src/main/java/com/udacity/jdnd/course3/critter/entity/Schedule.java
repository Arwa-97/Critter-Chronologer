package com.udacity.jdnd.course3.critter.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @Column(name = "schedule_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<Employee> employees;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<Pet> pets;

    @ElementCollection
    @CollectionTable(name="activities", joinColumns=@JoinColumn(name="schedule_id"))
    @Column(name = "activity")
    private Set<EmployeeSkill> activities;
    private LocalDate date;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
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
}
