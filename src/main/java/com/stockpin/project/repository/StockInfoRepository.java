package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.StockInfo;

public interface StockInfoRepository extends JpaRepository<StockInfo, String>{

}
