package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.CreditCard;

public interface CreditCardRepo extends JpaRepository<CreditCard, Integer> {

}
