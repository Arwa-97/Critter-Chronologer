package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Repository.Pet.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.User.CustomerRepository;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    private PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Customer save(Customer customer, List<Long> petIds){
        List<Pet> petList = new ArrayList<>();
        if (petIds != null && !petIds.isEmpty()) {
            for (int i = 0; i < petIds.size() ; i++) {
                Optional<Pet> pet = petRepository.findById(petIds.get(i));
                petList.add(pet.get());
            }
        }
        customer.setPets(petList);
        return customerRepository.save(customer);
    }
    public Customer findOwnerByPetId(Long petId){
        return petRepository.findById(petId).get().getCustomer();
    }
    public List<Customer> findAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }
}
