package com.app.controller;

import com.app.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final S3Service s3Service;

    @Autowired
    public ImageController(S3Service s3Service) {
        this.s3Service = s3Service;
    }
//  http://localhost:8080/api/images/uploadCarPhotos/{bucketName}/{propertyId}
    @PostMapping("/uploadCarPhotos/{bucketName}/{propertyId}")
    public ResponseEntity<String> uploadCarPhotos(@RequestParam("file") MultipartFile file,
                                                  @PathVariable String bucketName,
                                                  @PathVariable long propertyId) {
        try {
            String fileName = file.getOriginalFilename();
            // You can use bucketName and propertyId for any specific logic if needed
            s3Service.uploadFile(fileName, file);
            return new ResponseEntity<>("Car photo uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Car photo upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("Image upload service is up and running!", HttpStatus.OK);
    }
}

