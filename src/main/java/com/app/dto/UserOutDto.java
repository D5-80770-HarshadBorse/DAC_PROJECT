package com.app.dto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.entities.Booking;
import com.app.entities.CreditCard;
import com.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class UserOutDto {
	private int id;
	private String email;
	private String password;
    private String fname;
    private String lname;
    private String phone_no;
    private String dl_no;
    private String address;
    private List<Booking> bookings = new ArrayList<>();
    private Set<Role> roles = new HashSet<>();
    private CreditCard creditCard;
    
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}