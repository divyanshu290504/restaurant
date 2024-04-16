package com.bitssmart.smartRestaurant.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "payment")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "amount", nullable = false)
	private float amount;
	
	@Column(name = "paymentId")
	private String paymentId;
	
	@Column(name = "paymentMode")
	private String paymentMode;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Column(name = "bankTXNID")
	private String bankTXNID;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "gatewayName")
	private String gatewayName;
	
	@Column(name = "respCode")
	private String respCode;
	
	@Column(name = "repMsg")
	private String repMsg;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "txnAmount")
	private String txnAmount;
	
	@Column(name = "txnDate")
	private String txnDate;

	@ToString.Exclude
	@OneToOne
	@MapsId
    @JoinColumn(name = "order_id")
    private FoodOrder foodOrder;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
}
