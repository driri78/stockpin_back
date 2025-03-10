package com.stockpin.project.dto.index;

import java.util.Map;

import lombok.Getter;

@Getter
public class IndexPriceDTO {
    private String bsopHour; // 시간
	private String bstpNmixPrpr; // 현재가
	private String bstpNmixPrdyVrss; // 등락률
	private String acmlTrPbmn; // 거래대금
	private String acmlVol; // 거래량
	
	public IndexPriceDTO(Map<String, String> indexData) {
		this.bsopHour = indexData.get("bsop_hour");
		this.bstpNmixPrpr = indexData.get("bstp_nmix_prpr");
		this.bstpNmixPrdyVrss = indexData.get("bstp_nmix_prdy_vrss");
		this.acmlTrPbmn = indexData.get("acml_tr_pbmn");
		this.acmlVol = indexData.get("acml_vol");
	}
    	
}
