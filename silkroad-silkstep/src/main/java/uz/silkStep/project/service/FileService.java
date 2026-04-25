package uz.silkStep.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveAttachment(String fileName, String contentBase64);

    String saveAttachment(MultipartFile file);

    String getAttachmentFileLink(String fileName);
}
/// The FileService interface defines methods for handling file attachments in the application.
// It includes methods for saving attachments either from a base64 string or directly from a MultipartFile, as well as a method for retrieving the link to an attachment file. 
//This service abstracts the file storage mechanism, allowing for flexibility in how files are saved and accessed within the application.