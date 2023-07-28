package com.MCT.musicStreamingService.repository;

import com.MCT.musicStreamingService.model.AuthenticationToken;
import com.MCT.musicStreamingService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
