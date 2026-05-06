package com.app.controller;

import com.app.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actual-car-photos")
public class ActualCarPhotos {
    private S3Service s3Service;

    public ActualCarPhotos(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/uploadCarPhotos/{bucketName}/{propertyId}")
    public ResponseEntity<String> uploadCarPhotos(@RequestParam List<MultipartFile> files,
                                                  @PathVariable String bucketName,
                                                  @PathVariable long propertyId) {
        try {
            ArrayList<String> carImages=new ArrayList<>();
            for(MultipartFile file: files){

            String url=s3Service.uploadFile(bucketName,file);
            carImages.add(url);
            }
          // You can use bucketName and propertyId for any specific logic if needed
           //3Service.uploadFile(fileName, file);
           // return new ResponseEntity<>("Car photo uploaded successfully: " + fileName, HttpStatus.OK);
            return null;
        } catch (Exception e) {
            return new ResponseEntity<>("Car photo upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
