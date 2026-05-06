package com.app.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucketName}")
    private String defaultBucketName;

    public S3Service(@Value("${aws.accessKeyId}") String accessKeyId,
                     @Value("${aws.secretKey}") String secretKey,
                     @Value("${cloud.aws.region.auto}") boolean autoRegion) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(autoRegion ? Regions.DEFAULT_REGION.getName() : "ap-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        s3Client.putObject(new PutObjectRequest(defaultBucketName, fileName, file.getInputStream(), null));
        return fileName;
    }

    public void uploadFile(String bucketName, String fileName, MultipartFile file) throws IOException {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null));
    }
}

