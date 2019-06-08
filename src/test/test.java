package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.swu.dao.CriteriaFile;
import cn.edu.swu.dao.FileDAO;
import cn.edu.swu.dao.impl.UploadFileDao;
import cn.edu.swu.domain.FileUploadBean;

public class test {
	@Test
	public void testget() {
		FileDAO fileDAO =  new UploadFileDao();
		String file_name = "test";
		String file_desc = "1";
		CriteriaFile ff = new CriteriaFile(file_name, file_desc);
		System.out.println(ff.toString());
		
		//1. 调用AdminDAO 的getAll()得到Admin的集合
		List<FileUploadBean> fileUploadBeans = new ArrayList<FileUploadBean>();
		fileUploadBeans=fileDAO.getForListWithCriteriaFiles(ff);
		//for(FileUploadBean fileUploadBean: fileUploadBeans){
		System.out.println(fileUploadBeans.toString());
		//for(Admin admin:admins)
			//System.out.println(admin.getDate());
		//2. 把Admin的集合放入request中

	}
}
