package com.osw.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osw.order.model.CardDb;

public interface CardDbRepo extends JpaRepository<CardDb, Integer> {
	public List<CardDb> findByCardNumber(String cardNumber);
}
