package com.assignment.controller.mockito;

import static org.mockito.Mockito.lenient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.controller.DataProcessor;
import com.assignment.dao.Response;
import com.assignment.exception.BadRequestException;
import com.assignment.model.RequestDTO;
import com.assignment.repository.ResponseStoreRepository;
import com.assignment.service.DataProcessService;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessorTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataProcessor.class);

	@InjectMocks
	DataProcessor dataProcessor;
	
	@Mock 
	DataProcessService dataProcessService;
	
	@Mock
	ResponseStoreRepository responseStoreRepository;
	
	@Test
	public void testDataProcessor() {
		LOGGER.info("Entered testDataProcessor ");
		try {
		RequestDTO requestDTO = new RequestDTO();
		Integer[] arr = {35,2,100,15,75,25,99};
		requestDTO.setId(652);
		requestDTO.setFindDuplicates("HereWeHaveSomeDuplicatedCharacters");
		requestDTO.setWhiteSpacesGalore("Can we replace all these white spaces");
		requestDTO.setNumbers(arr);
		requestDTO.setShouldProcess(true);	
		Response response = new Response();
		response.setId(652);
		Response savedResponse = new Response();
		savedResponse.setId(1234);
		lenient().when(dataProcessService.saveResponse(response)).thenReturn(savedResponse);
		dataProcessor.processData(requestDTO);
		} catch (BadRequestException e) {
			LOGGER.error("Error occured in testDataProcessor"+e);
		}
		
	}
	
	@Test
	public void testDataProcessorException() {
		LOGGER.info("Entered testDataProcessorException ");
		try {
			RequestDTO requestDTO = new RequestDTO();
			dataProcessor.processData(requestDTO);
		} catch (BadRequestException e) {
			LOGGER.error("Error occured in testDataProcessor"+e);
		}
		
	}
}
