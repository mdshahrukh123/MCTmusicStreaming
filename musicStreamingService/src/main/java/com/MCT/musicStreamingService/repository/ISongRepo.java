package com.MCT.musicStreamingService.repository;

import com.MCT.musicStreamingService.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepo extends JpaRepository<Song,Integer> {
}
