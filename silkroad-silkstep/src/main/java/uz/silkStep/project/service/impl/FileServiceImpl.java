package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.silkStep.project.config.ApplicationProperties;
import uz.silkStep.project.service.FileService;
import uz.silkStep.project.service.MinioService;
import uz.silkStep.project.utils.FileUtils;

import java.io.IOException;
import java.util.Base64;

import static uz.silkStep.project.config.ApplicationProperties.Minio.Bucket.ATTACHMENTS;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MinioService minioService;
    private final ApplicationProperties applicationProperties;

    /**
     * Uploads a file received as {@link MultipartFile} to object storage.
     * <p>
     * This method generates a unique file name based on the current timestamp
     * and preserves the original file extension. The file content is then uploaded
     * to the configured storage bucket.
     * <p>
     * The returned value represents the stored file path (bucket + object name),
     * which can later be used for retrieval or generating download links.
     *
     * @param file the uploaded multipart file
     * @return the stored file path in the format "bucketName/fileName"
     */
    @Override
    public String saveAttachment(MultipartFile file) {
        String fileExtension = FileUtils.getFileExtension(file.getOriginalFilename());
        String fileName = getFileName(fileExtension);
        try {
            minioService.uploadFile(
                    applicationProperties.getMinio().getBucketNames().get(ATTACHMENTS),
                    fileName,
                    file.getBytes()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return applicationProperties.getMinio().getBucketNames().get(ATTACHMENTS) + "/" + fileName;
    }

    /**
     * Generates a unique file name using the current system timestamp.
     * <p>
     * This approach helps to avoid filename collisions in object storage.
     * The original file extension is preserved to maintain file type consistency.
     *
     * @param fileExtension the file extension (e.g., "png", "pdf")
     * @return a generated unique file name
     */
    private String getFileName(String fileExtension) {
        return System.currentTimeMillis() + "." + fileExtension;
    }

    /**
     * Uploads a file to object storage using Base64-encoded content.
     * <p>
     * The method decodes the Base64 string into binary data, generates a unique
     * file name, and uploads the content to the configured storage bucket.
     * <p>
     * This approach is typically used in APIs where files are transferred
     * as encoded strings instead of multipart uploads.
     *
     * @param originalFileName the original file name used to extract file extension
     * @param contentBase64 the Base64-encoded file content
     * @return the stored file path in the format "bucketName/fileName"
     */
    @Override
    public String saveAttachment(String originalFileName, String contentBase64) {
        String fileExtension = FileUtils.getFileExtension(originalFileName);
        String fileName = getFileName(fileExtension);

        minioService.uploadFile(
                applicationProperties.getMinio().getBucketNames().get(ATTACHMENTS),
                fileName,
                Base64.getDecoder().decode(contentBase64)
        );
        return applicationProperties.getMinio().getBucketNames().get(ATTACHMENTS) + "/" + fileName;
    }

    /**
     * Generates a temporary download link for a stored attachment.
     * <p>
     * This method creates a pre-signed URL with a limited validity period,
     * allowing secure access to the file without requiring authentication.
     * <p>
     * The link is typically used for client-side file downloads.
     *
     * @param fileName the object name stored in the bucket
     * @return a pre-signed URL valid for a short duration
     */
    @Override
    public String getAttachmentFileLink(String fileName) {
        return minioService.getDownloadLink(applicationProperties.getMinio().getBucketNames().get(ATTACHMENTS), fileName, 60 * 5);
    }
}

// The FileServiceImpl class provides implementations for handling file uploads and generating download links using MinIO as the underlying object storage. 
//It supports both multipart file uploads and Base64-encoded content, ensuring flexibility in how files can be received and stored.