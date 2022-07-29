package com.udacity.jdnd.course3.critter.Repository.Pet;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    @Query(value = "SELECT * FROM pet WHERE customer_id = :ownerId",
    nativeQuery = true)
    List<Pet> findPetsByOwnerId(Long ownerId);

    @Modifying
    @Query(value = "UPDATE customer SET pet_id = :id WHERE " +
            "customer_id = :customerId",
            nativeQuery = true)
    void updateCustomerPet(Long id, Long customerId);
}
