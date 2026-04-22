package uz.silkStep.project.service;

import java.io.InputStream;

public interface MinioService {
    void uploadFile(String bucketName, String name, String contentType, byte[] content);

    void uploadFile(String bucketName, String name, byte[] content);

    InputStream downloadFile(String bucketName, String name);

    String getDownloadLink(String bucketName, String name, int expiry);
}
