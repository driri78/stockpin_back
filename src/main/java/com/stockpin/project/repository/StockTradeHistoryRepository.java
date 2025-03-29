package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.StockTradeHistory;

public interface StockTradeHistoryRepository extends JpaRepository<StockTradeHistory, Long>{

}
