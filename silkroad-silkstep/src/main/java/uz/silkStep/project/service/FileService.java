package uz.silkStep.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveAttachment(String fileName, String contentBase64);

    String saveAttachment(MultipartFile file);

    String getAttachmentFileLink(String fileName);
}
