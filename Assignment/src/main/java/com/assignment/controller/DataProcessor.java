package com.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dao.Response;
import com.assignment.exception.BadRequestException;
import com.assignment.model.RequestDTO;
import com.assignment.service.DataProcessService;

@RestController
@RequestMapping("/incoming")
public class DataProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataProcessor.class);

	@Autowired
	private DataProcessService dataProcessService;
	
	@RequestMapping(value="/processData", method = RequestMethod.POST, produces = "application/json")
	public String processData(@RequestBody RequestDTO requestDTO) throws BadRequestException {
		Response response = new Response();
		
		if(requestDTO.isShouldProcess() &&  (requestDTO.getId()!=null || requestDTO.getId() >0 )) {
			response.setId(requestDTO.getId());
			if(!StringUtils.isEmpty(requestDTO.getFindDuplicates())) {
				response.setDuplicateChars(dataProcessService.findDuplicates(requestDTO.getFindDuplicates()));
			}else {
				LOGGER.error("Input String is null or empty");
				throw new BadRequestException("Please provide a valid String to find duplicates, it shouldn't be null or empty");
			}
			if(!StringUtils.isEmpty(requestDTO.getWhiteSpacesGalore())){
				
				response.setReplaceSpaces(dataProcessService.removeWhiteSpaces(requestDTO.getWhiteSpacesGalore()));
			}else {
				LOGGER.error("Input String is null or empty");
				throw new BadRequestException("Please provide a valid String to remove white spaces, it shouldn't be null or empty");
			}
			
			if(requestDTO.getNumbers()!=null) {
				response.setLargestNumber(dataProcessService.findLargestNumber(requestDTO.getNumbers()));
			}else {
				LOGGER.error("Input number array is null or empty");
				throw new BadRequestException("Please provide a valid numbers list, shouldn't be null or empty");
			}
			
		}else
		{
			LOGGER.error("Invalid input");
			throw new BadRequestException("Please provide a valid input");
		}
		Response savedResponse = dataProcessService.saveResponse(response);
		if(savedResponse!=null) {
			return "Processed the data and stored in DB successfully";
		}else {
			return "Failed to process the data";
		}
	}
}
