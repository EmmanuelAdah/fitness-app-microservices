package com.server.userservice.services.interfaces;

import com.server.userservice.dtos.response.UserResponse;

public interface UserService {

    UserResponse findById(String userId);
    Boolean existById(String userId);
}
