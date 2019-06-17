package cn.edu.swu.dao;

import java.util.List;

import cn.edu.swu.domain.FileUploadBean;;



public interface FileDAO {
	
	public List<FileUploadBean> getForListWithCriteriaFiles(CriteriaFile ff);
	
	public List<FileUploadBean> getALL();
	
	public void save(List<FileUploadBean> fileUploadBean);
	
	
	public FileUploadBean get(Integer id);
	
	public void  delete(Integer id);
	
	public long getCountWithFileName(String file_name);
	
	public long getCont(String file_name);
	
	
	
}
