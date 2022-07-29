package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.Repository.Pet.PetRepository;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    public Pet save(Pet pet){
        Pet response = petRepository.save(pet);
//        petRepository.updateCustomerPet(response.getId(), pet.getOwner().getId());
        return response;
    }
    public Optional<Pet> findById(Long id){
        return petRepository.findById(id);
    }
    public List<Pet> findAllPets(){
        return (List<Pet>) petRepository.findAll();
    }
    public List<Pet> findPetsByOwnerId(Long ownerId){
        List<Pet> pets = petRepository.findPetsByOwnerId(ownerId);
        return pets;
    }
}
