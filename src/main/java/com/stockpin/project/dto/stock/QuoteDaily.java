package com.stockpin.project.dto.stock;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QuoteDaily {
	private String stckBsopDate; // 일자
	private String stckClpr; // 종가
	private String chgRate; // 등락률 prdy_vrss(전일 대비 주가 상승량)
	private String acmlVol; // 거래량 (주)
	private String acmlTrPbmn; // 거래대금
	private String stckOprc; // 시가
	private String stckHgpr; // 고가
	private String stckLwpr; // 저가
	
	public QuoteDaily(Map<String, String> stockData) {
		this.stckBsopDate = stockData.get("stck_bsop_date");
		this.stckClpr = stockData.get("stck_clpr");
		this.chgRate = updateChgRate(stockData.get("prdy_vrss"));
		this.acmlVol = stockData.get("acml_vol");
		this.acmlTrPbmn = stockData.get("acml_tr_pbmn");
		this.stckOprc = stockData.get("stck_oprc");
		this.stckHgpr = stockData.get("stck_hgpr");
		this.stckLwpr = stockData.get("stck_lwpr");
	}
	
	public String updateChgRate(String prdyVrssStr) {
		if(prdyVrssStr.equals("0")) return "0.00";
		
		int stckClpr = Integer.parseInt(this.stckClpr);
		int prdyVrss = Integer.parseInt(prdyVrssStr);
		return String.valueOf(Math.round((double)prdyVrss / (stckClpr - prdyVrss) * 10000) / 100.0);
	}
	
}
