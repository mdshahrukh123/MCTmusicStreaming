package com.MCT.musicStreamingService.service;

import com.MCT.musicStreamingService.model.AuthenticationToken;
import com.MCT.musicStreamingService.model.Playlist;
import com.MCT.musicStreamingService.model.User;
import com.MCT.musicStreamingService.model.dto.SignInInput;
import com.MCT.musicStreamingService.model.dto.SignUpOutput;
import com.MCT.musicStreamingService.repository.IAuthTokenRepo;
import com.MCT.musicStreamingService.repository.IUserRepo;
import com.MCT.musicStreamingService.service.encryptionPassword.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAuthTokenRepo authTokenRepo;

    @Autowired
    PlaylistService playlistService;

    public SignUpOutput signUpUser(User user) {


        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getPassword());

            //save encrypted password

            user.setPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput("User registered successfully!!!",signUpStatus);
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }


    }

    public String signInUser(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user  email already exists ??
        User existingUser = userRepo.findFirstByEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authTokenRepo.save(authToken);

                return "SignIn Successfully Your Token is : "+authToken.getTokenValue();
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    public void signOutUser(String email) {
//        email -> User
        User user = userRepo.findFirstByEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByUser(user));

    }


    public void addPlaylist(String email, Playlist playlist) {
        User user = userRepo.findFirstByEmail(email);
        playlist.setUser(user);
        playlistService.addPlaylistByUser(playlist);
    }

    public Optional<Playlist> getPlaylist(String email) {
        User user = userRepo.findFirstByEmail(email);
        Integer userId = user.getUserId();

     return  playlistService.getAllPlaylistByUserId(userId);
    }


    public String removePlaylist(String email, Integer playlistId) {
        User user = userRepo.findFirstByEmail(email);
        Integer userId = user.getUserId();
        return playlistService.removePlaylistById(userId,playlistId);
    }
}
