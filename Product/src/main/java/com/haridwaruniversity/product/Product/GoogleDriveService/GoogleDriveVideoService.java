package com.haridwaruniversity.product.Product.GoogleDriveService;


import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.haridwaruniversity.product.Product.GoogleDriveService.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveVideoService {
    @Autowired
    private GoogleDriveService googleDriveService;

    public String uploadVideo(MultipartFile file) throws IOException, GeneralSecurityException {
        Drive driveService = googleDriveService.getDriveService();

        java.io.File tempFile = java.io.File.createTempFile("upload_", file.getOriginalFilename());
        Files.copy(file.getInputStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        String mimeType = Files.probeContentType(tempFile.toPath());
        if (mimeType == null) mimeType = "video/mp4";

        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setMimeType(mimeType);
        String folderId = "1AwvPZoE0Ikz3YHpG1pVPKncDV6MaL71k";
        fileMetadata.setParents(Collections.singletonList(folderId));

        FileContent mediaContent = new FileContent(mimeType, tempFile);
        File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id, webViewLink, webContentLink")
                .execute();

        Permission permission = new Permission().setType("anyone").setRole("reader");
        driveService.permissions().create(uploadedFile.getId(), permission).execute();

        return "Uploaded Video ID: " + uploadedFile.getId() +
                ", Link: " + uploadedFile.getWebViewLink();
    }

    public List<String> getAllVideos() throws IOException, GeneralSecurityException {
        Drive driveService = googleDriveService.getDriveService();

        List<String> videoList = new ArrayList<>();
        FileList result = driveService.files().list()
                .setQ("mimeType contains 'video/'")
                .setFields("files(id, name, webViewLink)")
                .execute();

        for (File file : result.getFiles()) {
            videoList.add("ID: " + file.getId() + ", Name: " + file.getName() + ", Link: " + file.getWebViewLink());
        }
        return videoList;
    }

    public String getVideoById(String videoId) throws IOException, GeneralSecurityException {
        Drive driveService = googleDriveService.getDriveService();
        File file = driveService.files().get(videoId)
                .setFields("id, name, webViewLink, webContentLink")
                .execute();
        return "ID: " + file.getId() + ", Name: " + file.getName() + ", Link: " + file.getWebViewLink();
    }
}