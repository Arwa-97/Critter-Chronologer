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

@Transactional
@Service
public class PetService {
    private PetRepository petRepository;
    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet save(Pet pet, long ownerId){
        Customer customer = customerRepository.findById(ownerId).get();
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.addPets(pet);
        customerRepository.save(customer);
        return pet;
    }
    public Optional<Pet> findById(Long id){
        return Optional.of(petRepository.findById(id).get());
    }
    public List<Pet> findAllPets(){
        return (List<Pet>) petRepository.findAll();
    }
    public List<Pet> findPetsByOwnerId(Long ownerId){
        List<Pet> pets = customerRepository.findById(ownerId).get().getPets();
        return pets;
    }
}
