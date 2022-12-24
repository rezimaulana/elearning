package com.lawencon.elearning.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.elearning.model.File;
import com.lawencon.elearning.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {
	
	@Autowired
	private FileService fileService;

    private final LocalDateTime now = LocalDateTime.now();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	
	@GetMapping("download/{id}")
    public ResponseEntity<?> download(@PathVariable("id") final Long id) {
        final String formatted = now.format(formatter);
        final Optional<File> file = fileService.getById(id);
        final byte[] fileBytes = Base64.getDecoder().decode(file.get().getFileCode());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attachment."+formatted+"." + file.get().getFileExt())
                .body(fileBytes);
    }
	
}