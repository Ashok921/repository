package com.assignment.service.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.service.DataProcessService;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessServiceTest {

	@Mock
	DataProcessService dataProcessService;
	
	@Test
	public void testRemoveWhiteSpaces() {
		
		String str = "Can we replace all these white spaces";
		dataProcessService.removeWhiteSpaces(str);		
		
	}
}
