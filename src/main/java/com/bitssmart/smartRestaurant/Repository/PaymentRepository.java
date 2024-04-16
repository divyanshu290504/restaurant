package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitssmart.smartRestaurant.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long > {

}
