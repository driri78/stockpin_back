package com.stockpin.project.dto.stock.info;

import java.util.Map;

import lombok.Getter;

@Getter
public class StockInfoDTO {
//	"cpta": "778046685000", 자본금
//	"scts_mket_lstg_dt": "19750611", 상장일
//	"lstg_stqt": "5919637922", 상장주 수
//  "prdt_eng_name120": "SamsungElectronics",
//	"std_idst_clsf_cd_name": "통신 및 방송 장비 제조업",
//  "idx_bztp_mcls_cd_name": "전기,전자",
	private String cpta;
	private String sctsMketLstgDt;
	private String lstgStqt;
	private String prdtEngName120;
	private String stdIdstClsfCdName;
	private String idxBztpMclsCdName;
	
	public StockInfoDTO(Map<String, String>StockData) {
		this.cpta = StockData.get("cpta");
		this.sctsMketLstgDt = StockData.get("scts_mket_lstg_dt");
		this.lstgStqt =  StockData.get("lstg_stqt");
		this.prdtEngName120 =  StockData.get("prdt_eng_name120");
		this.stdIdstClsfCdName =  StockData.get("std_idst_clsf_cd_name");
		this.idxBztpMclsCdName =  StockData.get("idx_bztp_mcls_cd_name");
	}
	
}
