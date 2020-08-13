package com.appdeveloperblog.photoappApi.users.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloperblog.photoappApi.users.service.UsersServiceImpl;
import com.appdeveloperblog.photoappApi.users.shared.UserDto;
import com.appdeveloperblog.photoappApi.users.ui.model.CreateUserModel;
import com.appdeveloperblog.photoappApi.users.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;

	@Autowired
	UsersServiceImpl usersImpl; 
	
	@GetMapping("/status/check")
	public String status() {
		
		return "working on port " + env.getProperty("local.server.port");
	}
	
	@PostMapping("/create")
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserModel userdetails) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = mapper.map(userdetails, UserDto.class);
		UserDto	createdUser= usersImpl.createUser(userDto);
		
		CreateUserResponseModel returnvalue = mapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnvalue);
	}
}
