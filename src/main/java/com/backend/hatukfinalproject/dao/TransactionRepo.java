package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hatukfinalproject.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
