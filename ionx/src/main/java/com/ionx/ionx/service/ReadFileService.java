package com.ionx.ionx.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ionx.ionx.domain.Prospect;

public interface ReadFileService {

	List<Prospect> findAll();

	boolean saveDataFromUploadfile(MultipartFile file);

}
