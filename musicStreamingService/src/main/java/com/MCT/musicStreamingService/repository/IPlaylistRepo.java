package com.MCT.musicStreamingService.repository;

import com.MCT.musicStreamingService.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaylistRepo extends JpaRepository<Playlist,Integer> {
}
