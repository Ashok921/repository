package com.booking.mapper;

import org.springframework.stereotype.Component;

import com.booking.dao.User;
import com.booking.model.UserDTO;

@Component
public class Mapper {

	public User mapUserDtoToUser(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setAddress(userDTO.getAddress());
		user.setEmailId(userDTO.getEmailId());
		user.setMobileNumber(userDTO.getMobileNumber());
		return user;
	}
	
	public UserDTO mapUserToUserDto(User user) {
		UserDTO userDTO= new UserDTO();
		userDTO.setMobileNumber(user.getMobileNumber());
		userDTO.setEmailId(user.getEmailId());
		userDTO.setAddress(user.getAddress());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		return userDTO;
	}
}
