package cn.edu.swu.dao;

public class CriteriaFile {
	private String file_name;
	private String file_desc;
	public String getFile_name() {
		if (file_name == null) {
			file_name = "%%";
		} else {
			file_name = "%" + file_name + "%";
 		}
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_desc() {
		if (file_desc == null) {
			file_desc = "%%";
		} else {
			file_desc = "%" + file_desc + "%";
 		}
		return file_desc;
	}
	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}
	public CriteriaFile(String file_name, String file_desc) {
		super();
		this.file_name = file_name;
		this.file_desc = file_desc;
	}
	@Override
	public String toString() {
		return "CriteriaFile [file_name=" + file_name + ", file_desc=" + file_desc + "]";
	}
	
}
