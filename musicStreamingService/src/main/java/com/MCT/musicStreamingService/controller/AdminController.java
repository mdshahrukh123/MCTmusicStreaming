package com.MCT.musicStreamingService.controller;

import com.MCT.musicStreamingService.model.Admin;
import com.MCT.musicStreamingService.model.Song;
import com.MCT.musicStreamingService.service.AdminService;
import com.MCT.musicStreamingService.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;


    @Autowired
    SongService songService;

    // create Admin
    @PostMapping("admin")
    public  String adminPost(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    // create Song by admin

    @PostMapping("admin/song")
    public String createSong(@RequestBody Song song){
        return songService.addSong(song);
    }
    // find song by admin
    @GetMapping("admin/song")
    public List<Song> findAllSong(){
        return songService.getAllSong();
    }

    // delete song
    @DeleteMapping("admin/song")
    public String removeSong(@RequestParam Integer id){
        return songService.deleteSong(id);
    }

    // Update title of song by admin
    @PutMapping("admin/song/title/{title}")
    public String updateTitleSong(@PathVariable String title,@RequestParam Integer songId){
        return songService.updateSongTitle(title,songId);
    }

}
