package com.webapp.trackingBoard.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
@Configuration
@Service
@ComponentScan
public interface UserService {
    boolean login(String email, String password);
}
