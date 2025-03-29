package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
