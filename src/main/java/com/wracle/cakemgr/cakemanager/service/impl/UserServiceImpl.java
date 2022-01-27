package com.wracle.cakemgr.cakemanager.service.impl;

import com.wracle.cakemgr.cakemanager.io.entity.UserEntity;
import com.wracle.cakemgr.cakemanager.io.repository.UserRepository;
import com.wracle.cakemgr.cakemanager.service.UserService;
import com.wracle.cakemgr.cakemanager.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //get user details from db
        Optional<UserEntity> userEntityOpt = userRepository.findByEmailId(email);
        if (!userEntityOpt.isPresent()) throw new UsernameNotFoundException(email);

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userEntityOpt.get(), UserDto.class);
        User user = new User(userDto.getEmailId(), userDto.getEncryptedPassword(), new ArrayList<>());
        return user;
    }
}
