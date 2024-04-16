package com.bitssmart.smartRestaurant.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "email_config")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class EmailConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "host")
	private String host;
	
	@Column(name = "sender")
	private String sender;
	
	@Column(name ="password")
	private String password;
	
	@Column(name = "port")
	private String port;
	
	@Column(name ="auth")
	private String auth;

	@Column(name ="sslEnable")
	private String sslEnable;

}
