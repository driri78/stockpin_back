package com.stockpin.project.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.stockpin.project.dto.stock.info.StockInfoDTO;
import com.stockpin.project.service.kis.ExternalStockInfoService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockInfoFacadeService {
	
	private final ExternalStockInfoService externalStockInfoService;
	
	// detail 종목정보
//	300: 주식, ETF, ETN, ELW
//	301 : 선물옵션
//	302 : 채권
//	306 : ELS'

//	cpta": "778046685000", 자본금
//	 "scts_mket_lstg_dt": "19750611", 상장일
//	"lstg_stqt": "5919637922", 상장주 수
//    "prdt_eng_name120": "SamsungElectronics",
//	"std_idst_clsf_cd_name": "통신 및 방송 장비 제조업",
//    "idx_bztp_mcls_cd_name": "전기,전자",
	public Mono<StockInfoDTO> getStockInfo(String typeCode, String code){
		return externalStockInfoService.getInfo(typeCode, code).flatMap(response -> {
			Map<String, String> stockData = (Map<String, String>)response.get("output");
			return Mono.just(new StockInfoDTO(stockData));
		});
	}
}
