package com.appdeveloperblog.photoappApi.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdeveloperblog.photoappApi.users.data.UserEntity;
import com.appdeveloperblog.photoappApi.users.data.UsersRepository;
import com.appdeveloperblog.photoappApi.users.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {

	UsersRepository usersRepo;
	
	@Autowired
	public UsersServiceImpl(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}
	
	@Override
	public UserDto createUser(UserDto userdetails) {

		userdetails.setUserId(UUID.randomUUID().toString());
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userdetails, UserEntity.class);
		userEntity.setEncryptedpassword("test");
		usersRepo.save(userEntity);
		
		UserDto returnvalue = mapper.map(userEntity, UserDto.class);
		return returnvalue;
	}

}
