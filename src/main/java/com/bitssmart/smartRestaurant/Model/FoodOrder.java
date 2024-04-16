package com.bitssmart.smartRestaurant.Model;

import java.util.Date;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "food_order")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "totalPrice", nullable = false)
	private float totalPrice;
	
	@Column(name = "isPaid", columnDefinition = "boolean default false", nullable = false)
	private Boolean isPaid;
	
	@ToString.Exclude
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private Tables tableId;
	
	@ToString.Exclude
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private User userId;
	
	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	@ToString.Exclude
	private List<OrderItem> orderItems;
	
	@ToString.Exclude
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private Customer customerID;
	
	@ToString.Exclude
	@OneToOne(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Payment payment;
	
	@Column(name = "orderStatus", nullable = false)
	private OrderStatus orderStatus;
	
	@Column(name = "orderType", nullable = false)
	private OrderType orderType;
	
	@Column(name = "otp")
	private String otp;
}
