package kr.co.ymtech.bm.controller.dto;

import java.util.List;

public class FileExplorerDTO {
      
   /** fileList : 파일 리스트 */
    private List<FileDTO> fileList;
    /** firstPath : 최상위 경로 */
    private String firstPath;

   public List<FileDTO> getFileList() {
      return fileList;
   }

   public String getFirstPath() {
      return firstPath;
   }

   public void setFileList(List<FileDTO> fileList) {
      this.fileList = fileList;
   }

   public void setFirstPath(String firstPath) {
      this.firstPath = firstPath;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("FileExplorerDTO [fileList=");
      builder.append(fileList);
      builder.append(", firstPath=");
      builder.append(firstPath);
      builder.append("]");
      return builder.toString();
   }
    
}