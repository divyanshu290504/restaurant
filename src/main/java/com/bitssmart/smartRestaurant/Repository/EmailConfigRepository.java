package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bitssmart.smartRestaurant.Model.EmailConfig;

public interface EmailConfigRepository extends JpaRepository<EmailConfig, Integer > {

}
