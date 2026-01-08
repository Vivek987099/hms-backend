package com.example.Public;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helperClasses.FileUpload;
@RestController
@RequestMapping("/file")
public class PublicController {
	@Autowired
	private FileUpload fileUpload;
	
	@GetMapping("/uploads/{fileName}")
	public ResponseEntity<Resource>  showMultipartFile(@PathVariable String fileName) throws IOException{
		  Resource resource= fileUpload.showFile(fileName);
		    String contentType ;
		    
		    if (fileName.toLowerCase().endsWith(".png")) {
		        contentType = "image/png";
		    } else if (fileName.toLowerCase().endsWith(".jpg") ||
		               fileName.toLowerCase().endsWith(".jpeg")) {
		        contentType = "image/jpeg";
		    } else if (fileName.toLowerCase().endsWith(".pdf")) {
		        contentType = "application/pdf";
		    } else {
		        contentType = "application/octet-stream";
		    }
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename=\"" + resource.getFilename() + "\"" ).body(resource);
		
	}

}
