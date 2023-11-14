package kr.co.ymtech.bm.service;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileExplorerService implements IFileExplorerService {
	
	@Override
	public void createFolder() {
		
		String path = "C:/FileExplorer/새폴더";
		File Folder = new File(path);
		
		if(!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.printf("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.printf("이미 폴더가 생성되어 있습니다.");
		}
		}
		
		
	}
	

	
	
	

