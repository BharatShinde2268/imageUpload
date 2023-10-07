package com.main.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.service.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// file original name
		String name = file.getOriginalFilename();
		System.out.println(name);
		// full path
		String filePath=path+ File.separator+name;
		
		// create folder if not created 
		
		File f=new File(name);
		if(!f.exists())
		{
			f.mkdir();
		}
		
		// file copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

}
