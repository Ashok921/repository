package com.assignment.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.dao.Response;
import com.assignment.repository.ResponseStoreRepository;

@Service
public class DataProcessService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataProcessService.class);

	@Autowired
	ResponseStoreRepository responseStoreRepository;
	
	public String findDuplicates(String inputString) {
		LOGGER.info("Inside findDuplicates method");
		int length = inputString.length();
		char[] duplicates = new char[length];
		String dups = "";
		boolean repeated = false ;
		for(int i=0;i<length;i++) {
			
			for(int k=0;k<length;k++) {
				if(duplicates[k]==inputString.charAt(i)) {
					repeated = true;
					break;
				}else {
					repeated = false;
				}
			}
			if(!repeated) {
				for(int j=i+1;j<length;j++) {					
					if((inputString.charAt(i)==inputString.charAt(j))) {
								duplicates[i] = inputString.charAt(i);
								break;
					}
				}
			}
		}
		dups = new String(duplicates);
		LOGGER.info("Duplicate characters inside the string are : "+dups);
		return dups;		
	}
	
	public String removeWhiteSpaces(String whiteSpacesGalore) {
		LOGGER.info("Inside removeWhiteSpaces method");
		String[] stringWithoutSpaces = whiteSpacesGalore.split("\\s");
		String replacedString = "";
		for(String s : stringWithoutSpaces) {
			if(replacedString.isEmpty()) {
				replacedString = replacedString+s;
			}else {
				replacedString = replacedString+"|"+s;	 
			}
					
		}
		LOGGER.info("String after replacement is : "+replacedString);
		return replacedString;
	}
	
	public Integer findLargestNumber(Integer[] numbers) {
		LOGGER.info("Inside findLargestNumber method");
		List<Integer> list = Arrays.asList(numbers);
		Integer largestNumber = Collections.max(list);	
		LOGGER.info("Largest number in the array is  : "+largestNumber);
		return largestNumber;
	}
	
	public Response saveResponse(Response response) {
		LOGGER.info("Inside saveResponse method");
		return responseStoreRepository.save(response);
	}
	
}
