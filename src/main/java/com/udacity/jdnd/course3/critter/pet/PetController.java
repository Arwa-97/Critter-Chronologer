package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = PetDTO.convertPetDTOToPet(petDTO);
        pet.setOwner(new Customer());
        pet.getOwner().setId(petDTO.getOwnerId());
        PetDTO response = PetDTO.convertPetToPetDTO(petService.save(pet));
        return response;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetDTO response = PetDTO.convertPetToPetDTO(petService.findById(petId).get());
        return response;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> response = PetDTO.convertPetToPetDTO(petService.findAllPets());
        return response;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> response = PetDTO.convertPetToPetDTO(petService.findPetsByOwnerId(ownerId));
        return response;
    }
}
