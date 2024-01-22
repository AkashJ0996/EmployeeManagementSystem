package com.inn.emp.Service.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inn.emp.Service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(MultipartFile file, String path) throws IOException {

		String originalFilename = file.getOriginalFilename();
		
		String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
		String FileName = UUID.randomUUID().toString();
		
		String newFileName = FileName + extension;
		String FileNameWithPath = path + newFileName ;
		
		File folder = new File(path);
		
		if(!folder.exists()) {
			folder.mkdir();
		}

		Files.copy(file.getInputStream(),Paths.get(FileNameWithPath));
		
		return newFileName ;
	}

	@Override
	public InputStream getResource(String image, String path) {
		String fileName=path+image;
		FileInputStream fis = null ;
		try {
	        fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fis;
		
	}

}
