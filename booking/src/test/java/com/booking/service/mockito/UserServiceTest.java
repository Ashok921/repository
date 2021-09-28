package com.booking.service.mockito;

import static org.mockito.Mockito.lenient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import com.booking.dao.User;
import com.booking.mapper.Mapper;
import com.booking.model.UserDTO;
import com.booking.repository.UserRepository;
import com.booking.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

	@InjectMocks
	private UserService userService;
	
	@Mock
	private Mapper mapper;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void registerUserTest() {
		
		LOGGER.info("Entering into registerUserTest");
		UserDTO userDTO = new UserDTO();
		userDTO.setAddress("abcd");
		userDTO.setEmailId("abc@xyz.com");
		userDTO.setFirstName("ashok");
		userDTO.setLastName("pandi");
		userDTO.setMobileNumber(1234567L);
		User user = new User();
		user.setAddress(userDTO.getAddress());
		user.setEmailId(userDTO.getEmailId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobileNumber(userDTO.getMobileNumber());
		Mockito.when(mapper.mapUserDtoToUser(userDTO)).thenReturn(user);
		userService.registerUser(userDTO);
		LOGGER.info("Exiting from registerUserTest");
	}
	
	@Test
	public void getUserDetailsTest() {
		LOGGER.info("Entering into getUserDetailsTest");
		Long mobNumber = 123L;
		User user = new User();
		user.setMobileNumber(123L);
		Optional<User> userDao = Optional.of(user);
		lenient().when(userRepository.findById(mobNumber)).thenReturn(userDao);
		userService.getUserDetails(mobNumber);
	}
	
	@Test
	public void getUserDetailsTestWithNoUser() {
		LOGGER.info("Entering into getUserDetailsTestWithNoUser");
		Long mobNumber = 123L;
		Optional<User> userDao = Optional.ofNullable(null);
		lenient().when(userRepository.findById(mobNumber)).thenReturn(userDao);
		userService.getUserDetails(mobNumber);
	}
	
	@Test
	public void getUserDetailsByEmailIdTest() {
		LOGGER.info("Entering into getUserDetailsByEmailIdTest");
		String emailId = "ashok@xyz.com";
		User user = new User();
		user.setEmailId(emailId);
		user.setMobileNumber(123L);
		user.setAddress("hyderabad");
		lenient().when(userRepository.findByEmailId(emailId)).thenReturn(user);
		userService.getUserDetailsByEmailId(emailId);	
	}
	
	@Test
	public void getUserDetailsByEmailIdTestWithNoUser() {
		LOGGER.info("Entering into getUserDetailsByEmailIdTestWithNoUser");
		String emailId = "ashok@xyz.com";
		User userDao = null;
		lenient().when(userRepository.findByEmailId(emailId)).thenReturn(userDao);
		userService.getUserDetailsByEmailId(emailId);
	}
	
	@Test
	public void getAllUsersTest() {
		LOGGER.info("Entering into getAllUsersTest");
		User user = new User();
		user.setEmailId("ashok@xyz.com");
		user.setMobileNumber(123L);
		user.setAddress("hyderabad");
		List<User> users = new ArrayList<User>();
		users.add(user);
		lenient().when(userRepository.findAll()).thenReturn(users);
		ReflectionTestUtils.setField(userService, "fileName", "anyString");
		userService.getAllUsers();
	}
	
	@Test
	public void getAllUsersWithNoUsers() {
		LOGGER.info("Entering into getAllUsersWithNoUsers");
		List<User> users = new ArrayList<User>();
		lenient().when(userRepository.findAll()).thenReturn(users);
		ReflectionTestUtils.setField(userService, "fileName", "anyString");
		userService.getAllUsers();
	}
	
}
