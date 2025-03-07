package com.stockpin.project.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Converter {

	/*
	 * public static List<Map<String, String>> ListOfMap(Object obj){
	 * List<Map<String,String>> result = new ArrayList<>(); if(obj instanceof
	 * List<?>) { for(Object item : (List<?>)obj) { if(item instanceof Map<?, ?>) {
	 * result.add((Map<String, String>)item); } } } return result;
	 * 
	 * }
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static List<Map<String, String>> convertTolistOfMap(Object obj){
		return objectMapper.convertValue(obj, new TypeReference<List<Map<String,String>>>() {});
	}
	
}
