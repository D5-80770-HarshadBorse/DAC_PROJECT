package com.app.services;

import java.util.List;

import com.app.dto.UserInDto;
import com.app.dto.UserOutDto;

public interface UserService {

	UserOutDto registerNewUser(UserInDto user);
	
	
	UserOutDto createUser(UserInDto user);

	UserOutDto updateUser(UserInDto user, Integer userId);

	UserOutDto getUserById(Integer userId);

	List<UserOutDto> getAllUsers();

	void deleteUser(Integer userId);
	
	UserOutDto changePassword(UserInDto user);


}
