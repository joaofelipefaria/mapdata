package br.com.jfelipefaria.mapdata.api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import br.com.jfelipefaria.mapdata.api.service.FileService;

/**
 * REST controller for uploading files and managing mapdata and metadata.
 * Handles HTTP requests and responses for the MapData API.
 */
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/files/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("FileController.uploadFile()");
    	if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "No file was provided"));
        }

        String fileName = fileService.storeFile(file);
        Map<String, String> response = new LinkedHashMap<>();
        response.put("message", "File uploaded successfully");
        response.put("filename", fileName);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/files/delete/{fileName}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable("fileName") String fileName) {
        System.out.println("FileController.deleteFile()");
    	fileService.deleteFile(fileName);

        return ResponseEntity.ok(Map.of(
                "message", "File deleted successfully",
                "filename", fileName
        ));
    }

    @GetMapping("/files/get/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable("fileName") String fileName) {
        System.out.println("FileController.getFile()");
    	Resource resource = fileService.getFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}