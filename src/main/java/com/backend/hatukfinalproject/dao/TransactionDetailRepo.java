package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hatukfinalproject.entity.TransactionDetail;

public interface TransactionDetailRepo extends JpaRepository<TransactionDetail, Integer> {

}
