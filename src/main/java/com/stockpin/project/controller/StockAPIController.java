package com.stockpin.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockpin.project.service.GetExcelDataService;
import com.stockpin.project.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StockAPIController {

	private final TokenService tokenService;
	
	private final GetExcelDataService getExcelDataService;
	
	@GetMapping("test")
	public List<List<String>> getDTO() {
		return getExcelDataService.readExcelFile("data/excel/kospi.xlsx");
	}
	
}
