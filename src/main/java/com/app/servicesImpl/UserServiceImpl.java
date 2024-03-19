package com.app.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.config.AppConstants;
import com.app.dto.UserInDto;
import com.app.dto.UserOutDto;
import com.app.entities.*;
import com.app.exceptions.*;
import com.app.repositories.*;
import com.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserOutDto createUser(UserInDto userInDto) {
        if(userRepo.findByEmail(userInDto.getEmail()).isPresent()) {
            throw new ExistingEntityException("User With Given Username Already Exists!");
            
    }
		User user = this.modelMapper.map(userInDto, User.class);
		User savedUser = this.userRepo.save(user);
		return this.modelMapper.map(savedUser, UserOutDto.class);
	}

	@Override
	public UserOutDto updateUser(UserInDto userInDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setEmail(userInDto.getEmail());
		user.setPhone_no(userInDto.getPhone_no());
		user.setAddress(userInDto.getAddress());

		User updatedUser = this.userRepo.save(user);
		UserOutDto userOutDto = this.modelMapper.map(updatedUser, UserOutDto.class);
		return userOutDto;
	}

	@Override
	public UserOutDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		return this.modelMapper.map(user, UserOutDto.class);
	}

	@Override
	public List<UserOutDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		List<UserOutDto> userOutDtos = users.stream().map(user -> this.modelMapper.map(user, UserOutDto.class)).collect(Collectors.toList());

		return userOutDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	@Override
	public UserOutDto registerNewUser(UserInDto userInDto) {
        if(userRepo.findByEmail(userInDto.getEmail()).isPresent()) {
            throw new ExistingEntityException("User With Given Username Already Exists!");
    }
		User user = this.modelMapper.map(userInDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserOutDto.class);
	}
	
	@Override
	public UserOutDto changePassword(UserInDto user) {
	
		User newUser=modelMapper.map(user, User.class);
		
		User u=userRepo.findById(newUser.getId()).orElseThrow();
		
		u.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
		
		userRepo.save(u);
		
		return this.modelMapper.map(u, UserOutDto.class);
		
		
	}

}
