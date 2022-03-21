package com.booking.service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.booking.dao.User;
import com.booking.mapper.Mapper;
import com.booking.model.UserDTO;
import com.booking.repository.UserRepository;



@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private String fileName;

	public User registerUser(UserDTO userDTO) {
		
		// Method to register User in database
		
		LOGGER.info("Entering into registerUser");
		User user = mapper.mapUserDtoToUser(userDTO);
		User userDao = userRepository.save(user);
		LOGGER.info("Registered the user successfully.");
		return userDao;
		
	}
	
	public UserDTO getUserDetails(Long mobileNumber){
		
		// Method to get user details using mobile number
		
		LOGGER.info("Entering into getUserDetails");
		UserDTO userDTO = null;
		Optional<User> userDao = userRepository.findById(mobileNumber);
		
		if(userDao.isPresent()) {
			User user = userDao.get();
			userDTO = mapper.mapUserToUserDto(user);
		}else {
			LOGGER.error("User Doesnot exist");
		}
		return userDTO;
		
	}
	
	public UserDTO getUserDetailsByEmailId(String emailId) {
		
		// Method to get user details by using email Id
		
		LOGGER.info("Entering into getUserDetailsByEmailId");
		UserDTO userDTO = null;
		User user = userRepository.findByEmailId(emailId);
		if(user!=null) {
			userDTO = mapper.mapUserToUserDto(user);
		}else {
			LOGGER.error("User Doesnot exist");
		}
		return userDTO;
	}
	
	public void getAllUsers() {
		
		// Method to get all users details in an excel sheet
		LOGGER.info("Entering into getAllUsers");
		List<User> users = userRepository.findAll();
		
		if(!CollectionUtils.isEmpty(users)) {
			Workbook wb = new HSSFWorkbook();
			HSSFSheet sheet = (HSSFSheet) wb.createSheet("users");
			HSSFRow rowHead = sheet.createRow(0);
			rowHead.createCell(0).setCellValue("Mobile Number");
			rowHead.createCell(1).setCellValue("First Name");
			rowHead.createCell(2).setCellValue("Last Name");
			rowHead.createCell(3).setCellValue("Address");
			rowHead.createCell(4).setCellValue("Email Id");
			int i = 1;
			for(User user : users) {
				HSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(user.getMobileNumber());
				row.createCell(1).setCellValue(user.getFirstName());
				row.createCell(2).setCellValue(user.getLastName());
				row.createCell(3).setCellValue(user.getAddress());
				row.createCell(4).setCellValue(user.getEmailId());
				i++;
			}
			try {
				FileOutputStream usersData = new FileOutputStream(fileName);
				wb.write(usersData);
				usersData.close();
				wb.close();
				LOGGER.info("Users Data file has been generated Successfully.");
			} catch (Exception e) {
				LOGGER.error("Exception occured while generating Excel file"+e);
			}  
			
		}
		
	}
}
