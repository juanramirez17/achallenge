package com.addi.challenge.config;

import com.addi.challenge.aggregate.UserAggregate;
import com.addi.challenge.domain.UserService;
import com.addi.challenge.domain.UserServiceImpl;
import com.addi.challenge.port.out.JudicialRecordService;
import com.addi.challenge.port.out.RegistryIdentificationService;
import com.addi.challenge.port.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JudicialRecordService judicialRecordService;

    @Autowired
    RegistryIdentificationService registryIdentificationService;

    @Bean
    public UserAggregate userAggregate() {
        return new UserAggregate(userService(), judicialRecordService, registryIdentificationService);
    }

    public UserService userService() {
        return new UserServiceImpl(userRepository);
    }

}
