package com.stockpin.project.service.component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.stockpin.project.dto.index.IndexPriceDTO;
import com.stockpin.project.service.module.ExternalIndexService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class IndexService {

	private final ExternalIndexService externalIndexService;
	
	public Mono<List<IndexPriceDTO>> getIndexFiveMinutePrice(String indexCode){
		return externalIndexService.getIndexPrice("300",indexCode).flatMap(response -> {
			List<Map<String, String>> res = (List<Map<String, String>>) response.get("output");
			List<IndexPriceDTO> result = res.stream()
										    .map(indexData -> {
											    return new IndexPriceDTO(indexData);
										    })
										    .collect(Collectors.toList());
			return Mono.just(result);
		});
	}
	
}
