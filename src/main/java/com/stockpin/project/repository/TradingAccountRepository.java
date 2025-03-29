package com.stockpin.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockpin.project.entity.TradingAccount;

public interface TradingAccountRepository extends JpaRepository<TradingAccount, Long>{

}
