package com.main.filecontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.payloads.FileResponse;
import com.main.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;
	
//	@Value("${project.image}")
//	private String path;
	
	String path="F:\\imageUpload\\imageupload\\src\\main\\resources\\templates\\images";
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image )
	{
		String file;
		try {
			file = fileService.uploadImage(path, image);
		} catch (IOException e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null,"image is not uploaded"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		return new ResponseEntity<>(new FileResponse(file,"image is successfully uploaded"),HttpStatus.OK);
	}
	
}
