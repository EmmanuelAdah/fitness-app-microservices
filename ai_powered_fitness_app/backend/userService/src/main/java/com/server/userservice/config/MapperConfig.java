package com.server.userservice.config;

import com.server.userservice.data.models.User;
import com.server.userservice.dtos.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<User, UserResponse> typeMap = modelMapper.typeMap(User.class, UserResponse.class);

        typeMap.addMappings(
                mapper -> mapper.skip(UserResponse::setRole)
        );

        return modelMapper;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
