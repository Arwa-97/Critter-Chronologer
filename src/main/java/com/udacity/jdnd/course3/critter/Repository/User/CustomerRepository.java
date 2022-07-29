package com.udacity.jdnd.course3.critter.Repository.User;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query(value = "SELECT c.* FROM customer c JOIN pet p ON c.customer_id = p.customer_id" +
            " WHERE p.pet_id = :petId",
    nativeQuery = true)
    Customer findOwnerByPetId(Long petId);
}
