package com.partycall.partycallback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.partycall.partycallback.dto.FileDTO;
import com.partycall.partycallback.dto.SavedFileDTO;
import com.partycall.partycallback.services.FileManagerService;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileManagerService fileManager;


    FileController(FileManagerService fileManager) {
        this.fileManager = fileManager;
    }
    
    @PostMapping("/upload")
    public ResponseEntity<SavedFileDTO> uploadFile(@RequestBody FileDTO fileDTO) {
        return ResponseEntity.ok(fileManager.uploadFile(fileDTO));
    }
    @GetMapping("/{fileName}/base64")
    public ResponseEntity<String> getFileInBase64(@PathVariable("fileName") String fileName) {
        return ResponseEntity.ok(fileManager.getFileInBase64(fileName));
    }
    @GetMapping("/{fileName}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        byte[] content = fileManager.getFileAsBytes(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, getFileMediaType(fileName))
                .header(HttpHeaders.CONTENT_DISPOSITION, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(content.length))
                .body(new ByteArrayResource(content));
    }
    @DeleteMapping("/{fileName:.+}")
    public ResponseEntity<Void> deleteFile(@PathVariable("fileName") String fileName) {
        fileManager.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }
    
    private String getFileMediaType(String fileName) {
        String mediaType;
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                mediaType = MediaType.APPLICATION_PDF_VALUE;
                break;
            case "png":
                mediaType = MediaType.IMAGE_PNG_VALUE;
                break;
            case "jpeg":
                mediaType = MediaType.IMAGE_JPEG_VALUE;
                break;
            default:
                mediaType = MediaType.TEXT_PLAIN_VALUE;
        }
        return mediaType;
    }
    
}
