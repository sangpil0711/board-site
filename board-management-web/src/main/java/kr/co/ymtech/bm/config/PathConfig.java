package kr.co.ymtech.bm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("file.path")
public class PathConfig {

	private String imagePath;
	private String explorerPath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getExplorerPath() {
		return explorerPath;
	}

	public void setExplorerPath(String explorerPath) {
		this.explorerPath = explorerPath;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PathConfig [imagePath=");
		builder.append(imagePath);
		builder.append(", explorerPath=");
		builder.append(explorerPath);
		builder.append("]");
		return builder.toString();
	}

}