package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>{

}
