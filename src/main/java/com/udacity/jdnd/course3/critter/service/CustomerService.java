package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Repository.Pet.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.User.CustomerRepository;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer findOwnerByPetId(Long petId){
        return customerRepository.findOwnerByPetId(petId);
    }
    public List<Customer> findAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }
}
