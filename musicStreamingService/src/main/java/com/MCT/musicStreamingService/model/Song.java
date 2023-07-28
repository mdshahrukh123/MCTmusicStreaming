package com.MCT.musicStreamingService.model;

import com.MCT.musicStreamingService.model.enums.GenreType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;
    private GenreType genre;
    private String title;
    private  String description;
    private String singerName;
    private LocalDateTime dateOfReleaseSong;
    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private Admin admin;


}
