package com.example.helperClasses;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

	public final String UPLOAD_DIR = "uploads";

	public Path uploadFile(MultipartFile file) throws IOException {

		Path uploadingDirectoryPath = Paths.get(UPLOAD_DIR);

//		CREATE DIRECTORY IF NOT EXIST
		createDirIfNotExist(uploadingDirectoryPath);

//     GET ORIGINAL FILE NAME 
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

//		CREATE FULL PATH WITH FILE NAME 
		Path filePath = uploadingDirectoryPath.resolve(fileName);

//       COPY FILE IN FOLDER IN UPLOADS DIRECTORY 
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return filePath;
	}

	public void updateFile(int id, MultipartFile file) throws IOException {

		String newFileName = file.getOriginalFilename();
//			createting path 

		Path uploadingPath = Paths.get(UPLOAD_DIR);
		Path newFilePath = uploadingPath.resolve(newFileName);
		

		Files.copy(file.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

	}

	public Resource showFile(String fileName) throws MalformedURLException {

		Path path = Paths.get("uploads").resolve(fileName).normalize();
		Resource resource = new UrlResource(path.toUri());
		if (!resource.exists()) {
			System.out.println("file does not exist");
		}

		return resource;

	}

	private void createDirIfNotExist(Path path) throws IOException {
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}

	}

}
