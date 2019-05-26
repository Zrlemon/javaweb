package cn.edu.swu.domain;

import java.util.Date;

public class FileUploadBean {


	
	private Integer id;
	// 文件名
	private String file_name;
	// 文件的路径
	private String file_path;
	// 文件的描述
	private String file_desc;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_desc() {
		return file_desc;
	}
	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "FileUploadBean [id=" + id + ", file_name=" + file_name + ", file_path=" + file_path + ", file_desc="
				+ file_desc + ", date=" + date + "]";
	}
	public FileUploadBean(String file_name, String file_path, String file_desc, Date date) {
		super();
		this.file_name = file_name;
		this.file_path = file_path;
		this.file_desc = file_desc;
		this.date = date;
	}
	public FileUploadBean() {
		super();
		// TODO Auto-generated constructor stub
	}



}
