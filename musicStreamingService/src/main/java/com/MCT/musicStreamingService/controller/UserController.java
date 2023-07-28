package com.MCT.musicStreamingService.controller;

import com.MCT.musicStreamingService.model.Playlist;
import com.MCT.musicStreamingService.model.User;
import com.MCT.musicStreamingService.model.dto.PlayListOutput;
import com.MCT.musicStreamingService.model.dto.SignInInput;
import com.MCT.musicStreamingService.model.dto.SignUpOutput;
import com.MCT.musicStreamingService.service.AuthTokenService;
import com.MCT.musicStreamingService.service.PlaylistService;
import com.MCT.musicStreamingService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthTokenService authTokenService;




    // create User And SignUp
    @PostMapping("user/signUp")
    public SignUpOutput userSignUp(@RequestBody User user){
        return userService.signUpUser(user);
    }

    // sign In user
    @PostMapping("user/signIn")
    public String userSignIn(@RequestBody SignInInput signInInput){
       return userService.signInUser(signInInput);
    }

    // sign out User
    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email,@RequestParam String token){
        if(authTokenService.authenticate(email,token)) {
            userService.signOutUser(email);
            return "Signed Out successfully.....!!!!";
        }
        else
        {
            return "SignOut failed because invalid authentication";
        }
    }

    // add playlist

    @PostMapping("user/playlist")
    public String addPlaylist(@RequestParam String email,@RequestParam String token,@RequestBody Playlist playlist){
        if(authTokenService.authenticate(email,token)) {
            userService.addPlaylist(email,playlist);
            return "playlist Added  successfully.....!!!!";
        }
        else
        {
            return "playlist add failed because invalid authentication";
        }

    }

    @GetMapping("user/playlist")
    public PlayListOutput getPlaylistByUser(@RequestParam String email, @RequestParam String token){
        Optional<Playlist> detailPlaylist = null;
        String statusMessage;
        if(authTokenService.authenticate(email,token)) {
           Optional<Playlist> list =  userService.getPlaylist(email);
           detailPlaylist = list;

            statusMessage = "Authenticated User details got successfully.....!!!!";
            return new PlayListOutput(detailPlaylist,statusMessage);
        }
        else
        {

            statusMessage = "Not able to get details because non Authenticate user.....!!!!";
            return new PlayListOutput(detailPlaylist,statusMessage);
        }
    }
    // delete playlist by user
    @DeleteMapping("user/playlist")
    public String removePlaylist(@RequestParam String email, @RequestParam String token,@RequestParam Integer playlistId){
        if(authTokenService.authenticate(email,token)) {
           return userService.removePlaylist(email,playlistId);

        }
        else
        {
            return "playlist delete failed because invalid authentication";
        }
    }
}
