package uz.silkStep.project.service;

import java.io.InputStream;

public interface MinioService {
    void uploadFile(String bucketName, String name, String contentType, byte[] content);

    void uploadFile(String bucketName, String name, byte[] content);

    InputStream downloadFile(String bucketName, String name);

    String getDownloadLink(String bucketName, String name, int expiry);
}

/// The MinioService interface defines a contract for interacting with a Minio object storage service. 
//It includes methods for uploading files (with or without specifying content type), downloading files as an InputStream, and generating download links with an expiration time. 
//This service abstracts the underlying storage mechanism and provides a simple API for file management within the application.