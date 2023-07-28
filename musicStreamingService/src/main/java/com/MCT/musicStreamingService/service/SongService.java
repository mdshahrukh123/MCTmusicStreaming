package com.MCT.musicStreamingService.service;

import com.MCT.musicStreamingService.model.Song;
import com.MCT.musicStreamingService.repository.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    ISongRepo songRepo;

    public String addSong(Song song) {
        songRepo.save(song);
        return "Song Added Successfully....!!!";
    }

    public String deleteSong(Integer id) {
        songRepo.deleteById(id);
        return "Song deleted By admin...!!!!!!";
    }

    public List<Song> getAllSong() {
        return songRepo.findAll();
    }

    public String updateSongTitle(String title,Integer songId) {
        Optional<Song> songOptional = songRepo.findById(songId);
        Song song = songOptional.get();
        song.setTitle(title);
        songRepo.save(song);
        return "Song title Updated Successfully...!!!!!";
    }
}
