package uz.silkStep.project.service.impl;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.silkStep.project.service.MinioService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

// This class implements the MinioService interface and provides methods for uploading and downloading files to/from an object storage service using the MinioClient.
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;

    /**
     * Uploads a file to the specified object storage bucket with an explicit content type.
     * <p>
     * This method streams the provided byte array into the storage system using the given object name.
     * The content type is explicitly set, allowing proper handling and interpretation
     * of the file during retrieval (e.g., browser rendering or API consumption).
     * <p>
     * Typically used when the MIME type of the file is known and must be preserved.
     *
     * @param bucketName the name of the target bucket in object storage
     * @param name the unique object name (file key) under which the file will be stored
     * @param contentType the MIME type of the file (e.g., "image/png", "application/pdf")
     * @param content the file content as a byte array
     */
    @Override
    public void uploadFile(String bucketName, String name, String contentType, byte[] content) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(name)
                            .contentType(contentType)
                            .stream(new ByteArrayInputStream(content), content.length, -1)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Uploads a file to the specified object storage bucket without explicitly defining a content type.
     * <p>
     * The file is stored as a binary object, and content type may be inferred later
     * or defaulted by the storage system or consuming client.
     * <p>
     * Suitable for cases where content type is either unknown or not critical.
     *
     * @param bucketName the name of the target bucket in object storage
     * @param name the unique object name (file key)
     * @param content the file content as a byte array
     */
    @Override
    public void uploadFile(String bucketName, String name, byte[] content) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(name)
                            .stream(new ByteArrayInputStream(content), content.length, -1)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Downloads a file from the specified object storage bucket.
     * <p>
     * This method retrieves the object as an {@link InputStream}, allowing
     * streaming of large files without loading them entirely into memory.
     * <p>
     * The caller is responsible for properly closing the returned stream
     * to prevent resource leaks.
     *
     * @param bucketName the name of the source bucket
     * @param name the unique object name (file key)
     * @return an {@link InputStream} for reading the file content
     */
    @Override
    public InputStream downloadFile(String bucketName, String name) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(name)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a pre-signed URL for downloading a file from object storage.
     * <p>
     * The generated URL provides temporary, secure access to the object without requiring
     * direct authentication. This is commonly used for exposing files to external clients.
     * <p>
     * The link expires after the specified duration, enhancing security by limiting access time.
     *
     * @param bucketName the name of the bucket containing the object
     * @param name the unique object name (file key)
     * @param expiry the expiration time in seconds for the generated URL
     * @return a pre-signed URL string that can be used to download the file
     */
    @Override
    public String getDownloadLink(String bucketName, String name, int expiry) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(name)
                            .expiry(expiry)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/// The MinioServiceImpl class provides concrete implementations for file upload and download operations using the MinioClient. 
//It handles both content-type-aware and content-type-agnostic uploads, as well as generating pre-signed URLs for secure file access.