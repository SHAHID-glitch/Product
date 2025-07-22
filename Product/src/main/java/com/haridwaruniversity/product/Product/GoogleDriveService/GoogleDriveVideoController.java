package com.haridwaruniversity.product.Product.GoogleDriveService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/videos")
public class GoogleDriveVideoController {

    @Autowired
    private GoogleDriveVideoService googleDriveVideoService;

    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file) throws IOException, GeneralSecurityException {
        if (file.getSize() > 2 * 1024 * 1024) { // 2MB limit
            return "File size exceeds 2MB limit!";
        }
        return googleDriveVideoService.uploadVideo(file);
    }
}