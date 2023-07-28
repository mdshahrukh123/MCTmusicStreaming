package com.MCT.musicStreamingService.model.dto;

import com.MCT.musicStreamingService.model.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class PlayListOutput {
    private Optional<Playlist> playlistList;
    private String statusMessage;
}
