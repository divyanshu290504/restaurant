package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bitssmart.smartRestaurant.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long > {
	
	public Customer findByPhoneNumber(String phoneNumber);

}
