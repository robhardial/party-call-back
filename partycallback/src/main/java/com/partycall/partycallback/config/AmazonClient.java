package com.partycall.partycallback.config;

import java.io.InputStream;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;


import java.io.File;
import java.io.IOException;

import jakarta.annotation.PostConstruct;

@Component
public class AmazonClient {

    private final Logger logger = LoggerFactory.getLogger(AmazonClient.class);

    private AmazonS3 s3client;

    @Value("${spring.amazonProperties.bucketName}")
    private String bucketName;

    @Value("${spring.amazonProperties.accessKey}")
    private String accessKey;

    @Value("${spring.amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct 
    private void initializeAmazonClient() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).build();
        createBucket();
    }

    public void uploadFileToBucket(String fileName, File file, String folderToUpload) {
        logger.info("Uploading file {} to {}", fileName, folderToUpload);
        s3client.putObject(new PutObjectRequest(bucketName, folderToUpload + "/" + fileName, file));
    }

    public void deleteFileFromBucket(String filename, String folderName) {
        logger.info("Deleting file {} from {}", filename, folderName);
        DeleteObjectRequest delObjReq = new DeleteObjectRequest(bucketName, folderName + "/" + filename);
        s3client.deleteObject(delObjReq);
    }

    public void deleteMultipleFilesFromBucket(List<String> files) {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                .withKeys(files.toArray(new String[0]));
        logger.info("Deleting files...");
        s3client.deleteObjects(delObjReq);
    }

    public File getFileFromBucket(String filename, String folderName) {
        InputStream inputStream = getFileInputStream(filename, folderName);
        File file = new File(filename);
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return file;
        }
        return file;
    }

    public InputStream getFileInputStream(String filename, String folderName) {
        S3Object s3object = s3client.getObject(bucketName, folderName + "/" + filename);
        return s3object.getObjectContent();
    }

    private void createBucket() {
        if (s3client.doesBucketExistV2(bucketName)) {
            logger.info("Bucket {} already exists", bucketName);
            return;
        }
        try {
            logger.info("Creating bucket {}", bucketName);
            s3client.createBucket(bucketName);
        } catch (Exception e) {
            logger.error((ExceptionUtils.getStackTrace(e)));
        }
    }
    
}
