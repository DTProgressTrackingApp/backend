package com.webapp.trackingBoard.service;

import com.webapp.trackingBoard.entities.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
@Configuration
@Service
@ComponentScan
public interface UserService {
    User login(String email, String password);
}
