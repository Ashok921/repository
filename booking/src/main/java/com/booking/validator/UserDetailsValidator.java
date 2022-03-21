package com.booking.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.booking.exception.BadRequestException;
import com.booking.model.UserDTO;
import com.booking.service.UserService;

@Component
public class UserDetailsValidator implements Validator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		// Method to validate user details for registering users
		
		LOGGER.info("Entering into UserDetailsValidator");
		UserDTO userDTO = (UserDTO)target;
		UserDTO user = new UserDTO();
		if(userDTO!=null) {
			
			if(StringUtils.isEmpty(userDTO.getFirstName())) {
				LOGGER.error("First name is null or empty");
				errors.reject("First name cannot be null or empty.");
			}
			if(StringUtils.isEmpty(userDTO.getLastName())) {
				LOGGER.error("Last name is null or empty");
				errors.reject("First name cannot be null or empty.");
			}
			if(StringUtils.isEmpty(userDTO.getAddress())) {
				LOGGER.error("Address is null or empty");
				errors.reject("Address can't be null or empty");
			}
			if(userDTO.getMobileNumber()==null) {
				LOGGER.error("Mobile number is null or empty");
				errors.reject("Mobile number can't be null");
			}else {
				user = userService.getUserDetails(userDTO.getMobileNumber());
				if(user!=null) {
					LOGGER.error("User already exists");
					errors.reject("User already exists with the mobile number please login");
				}
			}
			if(StringUtils.isEmpty(userDTO.getEmailId())) {
				errors.reject("email id can't be null or empty");
			}else {
				user = userService.getUserDetailsByEmailId(userDTO.getEmailId());
				if(user!=null) {
					LOGGER.error("User already exists");
					errors.reject("User already exists with the email Id , please login");
				}
			}
			
		}else 
		{
			LOGGER.error("Invalid input.");
			errors.reject("Enter valid input");
		}
		
	}

}
