package com.inn.emp.Service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String uploadImage(MultipartFile file , String path) throws IOException ;
	InputStream getResource(String image,String path);
}
