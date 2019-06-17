package cn.edu.swu.dao.impl;

import java.util.List;

import cn.edu.swu.dao.*;
import cn.edu.swu.domain.*;


public class UploadFileDao extends DAO<FileUploadBean> implements FileDAO{
	
	/*public List<FileUploadBean> getFiles(){
		
		//Connection conn = null;
		
		try {
			//conn = JdbcUtils.getConnection();
			String sql = "SELECT id, file_name fileName, file_path filePath, " +
					"file_desc fileDesc FROM upload_files";
			return getForList(sql);			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	} 
	
	public void save1(List<FileUploadBean> uploadFiles){
		
		//Connection conn = null;
		
		try {
			//conn = JdbcUtils.getConnection();
			//System.out.println(1);
			String sql = "INSERT INTO upload_files (file_name, file_path, file_desc) VALUES " +
					"(?, ?, ?)";
			//System.out.println(2);
			for(FileUploadBean file: uploadFiles){
				update(sql,file.getFileName(), file.getFilePath(), file.getFileDesc());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}*/
	
	@Override
	public List<FileUploadBean> getForListWithCriteriaFiles(CriteriaFile ff) {
		// TODO Auto-generated method stub
		String sql = "SELECT id,file_name,file_path,file_desc,date FROM upload_files WHERE file_name LIKE ? AND file_desc LIKE ? ";
		return getForList(sql, ff.getFile_name(),ff.getFile_desc());
	}

	@Override
	public List<FileUploadBean> getALL() {
		// TODO Auto-generated method stub
		String sql = "SELECT id,file_name,file_path,file_desc,date FROM upload_files";
		return getForList(sql);
	}

	@Override
	public void save(List<FileUploadBean>  beans) {
		// TODO Auto-generated method stub

			String sql = "INSERT INTO upload_files (file_name, file_path, file_desc) VALUES " +
					"(?, ?, ?)";
			//System.out.println(2);
			
			for(FileUploadBean file: beans){
				update(sql,file.getFile_name(), file.getFile_path(), file.getFile_desc());
			}
						
		
		
	}

	@Override
	public FileUploadBean get(Integer id) {
		String sql = "SELECT id,file_name,file_path,file_desc,date FROM upload_files WHERE id = ?";
		return get(sql,id);
	}

	@Override
	public long getCountWithFileName(String file_name) {
		String sql = "SELECT count(id) FROM upload_files WHERE name = ?";
		return getForValue(sql, file_name);
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM upload_files WHERE id = ?";
		update(sql, id);
		
	}


	@Override
	public long getCont(String file_name) {
		String sql = "SELECT count(id) FROM upload_files WHERE file_name like ?";
		return getForValue(sql, file_name);
	}
}
