package com.MCT.musicStreamingService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer playlistId;
    private String playlistName;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "fk_song_id")
    private Song song;
}
