package br.com.jfelipefaria.mapdata.api.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service class for business logic related to file uploads. Handles file
 * storage and management. Annotated with @Service to be managed by Spring's
 * dependency injection.
 */
@Service
public class FileService {

	private static final Path UPLOAD_DIR = Paths.get("uploads");


	public String storeFile(MultipartFile file) {
		try {
			if (Files.notExists(UPLOAD_DIR)) {
				Files.createDirectories(UPLOAD_DIR);
			}
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Path target = UPLOAD_DIR.resolve(fileName);
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException("Could not store file.", e);
		}
	}

	public String deleteFile(String fileName) {
		try {
			if (Files.notExists(UPLOAD_DIR)) {
				Files.createDirectories(UPLOAD_DIR);
			}
			Path target = UPLOAD_DIR.resolve(fileName);
			Files.deleteIfExists(target);
			return fileName;
		} catch (IOException e) {
			throw new RuntimeException("Could not delete file.", e);
		}
	}

	public Resource getFile(String fileName) {
		try {
			if (Files.notExists(UPLOAD_DIR)) {
				Files.createDirectories(UPLOAD_DIR);
			}
			Path target = UPLOAD_DIR.resolve(fileName);
			if (Files.notExists(target)) {
				throw new RuntimeException("File not found: " + fileName);
			}
			Resource resource = new UrlResource(target.toUri());
			if (!resource.exists() || !resource.isReadable()) {
				throw new RuntimeException("Could not read file: " + fileName);
			}
			return resource;
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file.", e);
		} catch (IOException e) {
			throw new RuntimeException("Could not read file.", e);
		}
	}
}