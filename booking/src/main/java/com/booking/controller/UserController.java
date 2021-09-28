package com.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dao.User;
import com.booking.exception.BadRequestException;
import com.booking.exception.DataNotFoundException;
import com.booking.model.UserDTO;
import com.booking.service.UserService;
import com.booking.validator.UserDetailsValidator;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsValidator userDetailsValidator;
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully registered user"), @ApiResponse(code = 400, message = "Missing or invalid request body"), @ApiResponse(code = 500, message = "Internal error")})
	public String registerUser(@RequestBody UserDTO userDTO,BindingResult errors) throws BindException {
		LOGGER.info("Entering into registerUser method");
		ValidationUtils.invokeValidator(userDetailsValidator, userDTO, errors);
		if(errors.hasErrors()) {
			throw new BindException(errors);
		}
		User user = userService.registerUser(userDTO);
		if(user!=null) {
			LOGGER.info("Registered the user successfully.");
			return "Successfully registered the user ";
		}else {
			LOGGER.error("Error occured while registering the user.");
			return "User registration failed !!";
		}
		
	}
	
	@RequestMapping(value="/getUserByMobNumber/{mobileNumber}", method = RequestMethod.GET,produces = "application/json")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved user details"), @ApiResponse(code = 400, message = "Missing or invalid request body"), @ApiResponse(code = 500, message = "Internal error")})
	public UserDTO getUserDetailsByMobileNumber(@PathVariable Long mobileNumber) throws BadRequestException, DataNotFoundException {
		LOGGER.info("Entering into getUserDetailsByMobileNumber");
		if(mobileNumber==null) {
			
			throw new BadRequestException("please enter a valid mobile number");
		}
		UserDTO userDto = userService.getUserDetails(mobileNumber);
		if(userDto==null) {
			throw new DataNotFoundException("User doesn't exist with the corresponding mobile number");
		}
		return userDto;
		
	}
	
	@RequestMapping(value="/getUserByEmailId/{emailId}", method = RequestMethod.GET,produces = "application/json")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved user details"), @ApiResponse(code = 400, message = "Missing or invalid request body"), @ApiResponse(code = 500, message = "Internal error")})
	public UserDTO getUserDetailsByEmailId(@PathVariable String emailId) throws BadRequestException, DataNotFoundException {
		LOGGER.info("Entering into getUserDetailsByEmailId");
		if(StringUtils.isEmpty(emailId)) {
			throw new BadRequestException("please enter a valid mobile number");
		}
		UserDTO userDto = userService.getUserDetailsByEmailId(emailId);
		if(userDto==null) {
			throw new DataNotFoundException("User doesn't exist with the corresponding emailId");
		}
		return userDto;
		
	}
	
	@RequestMapping(value="/getAllUsers", method = RequestMethod.GET,produces = "application/json")
	public void getAllUsers() {
		LOGGER.info("Entering into getAllUsers");
		userService.getAllUsers();
	}
}
