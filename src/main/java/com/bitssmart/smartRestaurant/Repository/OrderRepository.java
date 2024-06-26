package com.bitssmart.smartRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;
import com.bitssmart.smartRestaurant.Model.UserRoles;

@Repository
public interface OrderRepository extends JpaRepository<FoodOrder, Long > {

	@Query("SELECT a FROM FoodOrder a WHERE orderStatus in (:orderStatus) AND orderType = :orderType")
	public List<FoodOrder> findByOrderStatus(@Param("orderStatus") List<OrderStatus> orderStatus, @Param("orderType") OrderType orderType);

	@Query("SELECT a FROM FoodOrder a WHERE customerID.id =:custid")
	public List<FoodOrder> findByCustomerId(@Param("custid") Long id);
	
	@Query("SELECT a FROM FoodOrder a WHERE userId.id =:delId and userId.userRoles=:delivery_guy")
	public List<FoodOrder> findByDeliveryGuy(@Param("delId") Long id,@Param("delivery_guy") UserRoles userRoles);
}
