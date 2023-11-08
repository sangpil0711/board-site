package kr.co.ymtech.bm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("file.path")
public class ImageConfig {

   private String imagePath;

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("ImageConfig [imagePath=");
      builder.append(imagePath);
      builder.append("]");
      return builder.toString();
   }
   
}