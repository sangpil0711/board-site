package kr.co.ymtech.bm.controller.dto;

public class SaveFolderDTO {

	/** name : 폴더 이름 */
	private String name;
	/** Path : 폴더 경로 */
	private String path;
	/** newFolderName : 생성한 폴더 이름 */
	private String newFolderName;

	public String getPath() {
		return path;
	}

	public String getNewFolderName() {
		return newFolderName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setNewFolderName(String newFolderName) {
		this.newFolderName = newFolderName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveFolderDTO [name=");
		builder.append(name);
		builder.append(", path=");
		builder.append(path);
		builder.append(", newFolderName=");
		builder.append(newFolderName);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}
