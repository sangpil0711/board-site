package kr.co.ymtech.bm.service;

import java.util.List;

import kr.co.ymtech.bm.controller.dto.FileDTO;


public interface IFileExplorerService {

	public void createFolder();

	public List<FileDTO> loadAllFiles(String path);



}
