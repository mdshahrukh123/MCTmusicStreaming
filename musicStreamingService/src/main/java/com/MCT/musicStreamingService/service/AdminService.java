package com.MCT.musicStreamingService.service;

import com.MCT.musicStreamingService.model.Admin;
import com.MCT.musicStreamingService.model.Song;
import com.MCT.musicStreamingService.repository.IAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;


    public String createAdmin(Admin admin) {
        adminRepo.save(admin);
        return "Admin Added Successfully....!!!!!";
    }
}
