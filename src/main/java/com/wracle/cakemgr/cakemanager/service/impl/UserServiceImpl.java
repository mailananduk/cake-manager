package com.wracle.cakemgr.cakemanager.service.impl;

import com.wracle.cakemgr.cakemanager.repository.UserRepository;
import com.wracle.cakemgr.cakemanager.repository.dao.UserDao;
import com.wracle.cakemgr.cakemanager.service.UserService;
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
        Optional<UserDao> userDaoOpt = userRepository.findByEmailId(email);
        if (!userDaoOpt.isPresent()) throw new UsernameNotFoundException(email);

        UserDao userDao = userDaoOpt.get();
        User user = new User(userDao.getEmailId(), userDao.getEncryptedPassword(), new ArrayList<>());
        return user;
    }
}
