package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if(customer.getPets() != null) {
            for (int i = 0; i < customer.getPets().size(); i++) {
                List<Long> petIds = new ArrayList<>();
                petIds.add(customer.getPets().get(i).getId());
                customerDTO.setPetIds(petIds);
            }
        }
        return customerDTO;
    }
    public static List<CustomerDTO> convertCustomerToCustomerDTO(List<Customer> customers){
        List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO customerDTO= new CustomerDTO();
            BeanUtils.copyProperties(customers.get(i), customerDTO);
            if(customers.get(i).getPets() != null) {
                List<Long> petIds = new ArrayList<>();
                petIds.add(customers.get(i).getPets().get(i).getId());
                customerDTO.setPetIds(petIds);
            }
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
    public static Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }
}
