package com.MCT.musicStreamingService.service;

import com.MCT.musicStreamingService.model.Playlist;
import com.MCT.musicStreamingService.repository.IPlaylistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    IPlaylistRepo playlistRepo;



    public void addPlaylistByUser(Playlist playlist) {
        playlistRepo.save(playlist);
    }

    public Optional<Playlist> getAllPlaylistByUserId(Integer userId) {
        return playlistRepo.findById(userId);
    }


    public String removePlaylistById(Integer userId, Integer playlistId) {
        Optional<Playlist> optPlaylist = playlistRepo.findById(playlistId);
        Playlist playlistNew = optPlaylist.get();
        Integer fkOfPlayList = playlistNew.getUser().getUserId();
        if(fkOfPlayList == userId){
            playlistRepo.deleteById(userId);
            return "Playlist Deleted Successfully....!!!!!";
        }
        return "User and Playlist didn't matched...!!!!!";
    }
}
