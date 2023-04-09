package com.filetask.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class FileUploadDownloadController {
	
	private static final Map<String, String> fileIdToUrl = new HashMap<>();

	@PostMapping("uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

		String pathname = "R://code/FileUploadShoot/file-_upload-_download-shoot/files/" + multipartFile.getOriginalFilename();
		File file = new File(pathname);
		
		file.createNewFile();
		
		String fileId = UUID.randomUUID().toString().replace("-", "");
		fileIdToUrl.put(fileId, pathname);
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			fileOutputStream.write(multipartFile.getBytes());
		}
		log.info("file Name : {}", multipartFile.getName());
		log.info("file contenType : {}", multipartFile.getContentType());
		log.info("file orignalFileName : {}", multipartFile.getOriginalFilename());
		log.info("file Size : {}", multipartFile.getSize());
		return "File uploded and file is is " + fileId;

	}
	
	@GetMapping("downloadFile/{file-id}")
	public void downloadFile(@PathVariable("file-id") String fileId , HttpServletResponse response) throws IOException {

		String fileUrl = fileIdToUrl.get(fileId);
		
		File file = new File(fileUrl);
		byte[] fileBytes = Files.readAllBytes(file.toPath());
		
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+file.getName());
	
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(fileBytes);
		
		
		
		
	}
	
	@GetMapping("fileIds")
	public Map<String, String> getAllFileIds()  {

		return this.fileIdToUrl;

}
}
