package kr.co.ymtech.bm.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.config.ImagePathConfig;
import kr.co.ymtech.bm.controller.dto.FileDTO;

@Service
public class FileExplorerService implements IFileExplorerService {

   private final ImagePathConfig imagePathConfig;

   public FileExplorerService(ImagePathConfig imagePathConfig) {
      this.imagePathConfig = imagePathConfig;
   }

   @Override
   public List<FileDTO> loadAllFiles(String path, int depth, String name) {
      File file = null;

      if (path == null && depth == 0) {
         file = new File(imagePathConfig.getFilePath());
         path = imagePathConfig.getFilePath();
      } else {
         file = new File(Paths.get(path).resolve(name).normalize().toString());
         path = Paths.get(path).resolve(name).normalize().toString();
      }

      File[] files = file.listFiles();
      List<FileDTO> list = new ArrayList<>();
      FileDTO dto = null;

      if (files != null) {
         for (File f : files) {

            dto = new FileDTO();

            dto.setName(f.getName());
            dto.setIsDirectory(f.isDirectory());
            dto.setPath(path);
            dto.setDepth(depth);

            list.add(dto);
         }
      }

      return list;
   }

//   @Override
//   public void createFolder() {
//
//      String path = "C:/FileExplorer/새폴더";
//      File Folder = new File(path);
//
//      if (!Folder.exists()) {
//         try {
//            Folder.mkdir(); // 폴더 생성합니다.
//            System.out.printf("폴더가 생성되었습니다.");
//         } catch (Exception e) {
//            e.getStackTrace();
//         }
//      } else {
//         System.out.printf("이미 폴더가 생성되어 있습니다.");
//      }
//   }

}