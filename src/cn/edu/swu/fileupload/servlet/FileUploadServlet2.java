package cn.edu.swu.fileupload.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import cn.edu.swu.dao.impl.*;
import cn.edu.swu.domain.*;
import cn.edu.swu.fileupload.exception.InvalidExtNameException;
import cn.edu.swu.fileupload.utils.FileUploadAppProperties;
import cn.edu.swu.fileupload.utils.FileUtils;

public class FileUploadServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String FILE_PATH = "/WEB-INF/files";
	
	private static final String TEMP_DIR = "/WEB-INF/tempDirectory";
	
	private UploadFileDao dao = new UploadFileDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String path = null;
		//System.out.println("success");
		
		//获取 ServletFileUpload 对象. 
		ServletFileUpload upload = getServletFileUpload();
		
		try {
			
			//把需要上传的 FileItem 都放入到该 Map 中
			//键: 文件的待存放的路径, 值: 对应的 FileItem 对象
			Map<String, FileItem> uploadFiles = new HashMap<String, FileItem>();
			
			//解析请求, 得到 FileItem 的集合.
			List<FileItem> items = upload.parseRequest(request);
			
			//1. 构建 FileUploadBean 的集合, 同时填充 uploadFiles
			List<FileUploadBean> beans = buildFileUploadBeans(items, uploadFiles);
			
			//2. 校验扩展名:
			vaidateExtName(beans);

			//3. 校验文件的大小: 在解析时, 已经校验了, 我们只需要通过异常得到结果. 
			
			//4. 进行文件的上传操作.
			upload(uploadFiles);
			
			//5. 把上传的信息保存到数据库中
			saveBeans(beans);
			System.out.println("success");
			
			//6. 删除临时文件夹的临时文件
			FileUtils.delAllFile(TEMP_DIR);
			
			
			path = request.getContextPath()+"/users/fileupload.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
		}
		response.sendRedirect(path);
		//request.getRequestDispatcher(path).forward(request, response);
	}

	private void saveBeans(List<FileUploadBean> beans) {
		dao.save(beans); 
	}

	/**
	 * 文件上传前的准备工作. 得到 filePath 和 InputStream
	 * @param uploadFiles
	 * @throws IOException
	 */
	private void upload(Map<String, FileItem> uploadFiles) throws IOException {
		for(Map.Entry<String, FileItem> uploadFile: uploadFiles.entrySet()){
			String filePath = uploadFile.getKey();
			FileItem item = uploadFile.getValue();
			
			upload(filePath, item.getInputStream());
		}
	}

	/**
	 * 文件上传的 IO 方法.
	 * 
	 * @param filePath
	 * @param inputStream
	 * @throws IOException
	 */
	private void upload(String filePath, InputStream inputStream) throws IOException {
		OutputStream out = new FileOutputStream(filePath);
		
		byte [] buffer = new byte[1024];
		int len = 0;
		
		while((len = inputStream.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		
		inputStream.close();
		out.close();
		
		//System.out.println(filePath); 
	}

	/**
	 * 校验扩展名是否合法
	 * @param beans: 
	 */
	private void vaidateExtName(List<FileUploadBean> beans) {
		String exts = FileUploadAppProperties.getInstance().getProperty("exts");
		List<String> extList = Arrays.asList(exts.split(","));
		//System.out.println(extList);
		
		for(FileUploadBean bean: beans){
			String fileName = bean.getFile_name();
			System.out.println(fileName.indexOf(".")); 
			
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
			//System.out.println(extName); 
			
			if(!extList.contains(extName)){
				throw new InvalidExtNameException(fileName + "文件的扩展名不合法");
			}
		}
	}

	/**
	 * 利用传入的 FileItem 的集合, 构建 FileUploadBean 的集合, 同时填充 uploadFiles
	 * 
	 * FileUploadBean 对象封装了: id, fileName, filePath, fileDesc
	 * uploadFiles: Map<String, FileItem> 类型, 存放文件域类型的  FileItem. 键: 待保存的文件的名字 ,值: FileItem 对象
	 * 
	 * 构建过程:
	 * 1. 对传入 FileItem 的集合进行遍历. 得到 desc 的那个 Map. 键: desc 的 fieldName(desc1, desc2 ...). 
	 * 值: desc 的那个输入的文本值
	 * 
	 * 2. 对传入 FileItem 的集合进行遍历. 得到文件域的那些 FileItem 对象, 构建对应的 key (desc1 ....) 来获取其 desc.
	 * 构建的 FileUploadBean 对象, 并填充 beans 和 uploadFiles
	 * 
	 * @param items
	 * @param uploadFiles
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private List<FileUploadBean> buildFileUploadBeans(List<FileItem> items, Map<String, FileItem> uploadFiles) throws UnsupportedEncodingException {
		List<FileUploadBean> beans = new ArrayList<>();
		
		Map<String, String> descs = new HashMap<>();
		
		for(int i = 0; i < items.size(); i++){
			FileItem item = items.get(i);
			
			if(item.isFormField()){
				//desc1 或 desc2 ...
				String fieldName = item.getFieldName();
				String desc = item.getString("UTF-8");
				
				descs.put(fieldName, desc);
			}
		}
		
		for(int i = 0; i < items.size(); i++){
			FileItem item = items.get(i);
			FileUploadBean bean = null;
			if(!item.isFormField()){
				String fieldName = item.getFieldName();
				String descName = "desc" + fieldName.substring(fieldName.length() - 1);
				String desc = descs.get(descName); 
				
				//对应文件名
				String fileName = item.getName();
				String filePath = getFilePath(fileName);
				
				bean = new FileUploadBean(fileName, filePath, desc, null);
				beans.add(bean);
				
				uploadFiles.put(bean.getFile_path(), item);
			}			
		}
		
		return beans;
	}

	/**
	 * 根据跟定的文件名构建一个随机的文件名
	 * 1. 构建的文件的文件名的扩展名和给定的文件的扩展名一致
	 * 2. 利用 ServletContext 的 getRealPath 方法获取的绝对路径
	 * 3. 利用了 Random 和 当前的系统时间构建随机的文件的名字
	 * 
	 * @param fileName
	 * @return
	 */
	private String getFilePath(String fileName) {
		String extName = fileName.substring(fileName.lastIndexOf("."));
		Random random = new Random();
		
		String filePath = getServletContext().getRealPath(FILE_PATH) + "/" + System.currentTimeMillis() + random.nextInt(100000) + extName;
		return filePath;
	}

	/**
	 * 构建 ServletFileUpload 对象
	 * 从配置文件中读取了部分属性, 用户设置约束. 
	 * 该方法代码来源于文档. 
	 * @return
	 */
	private ServletFileUpload getServletFileUpload() {
		String fileMaxSize = FileUploadAppProperties.getInstance().getProperty("file.max.size");
		String totalFileMaxSize = FileUploadAppProperties.getInstance().getProperty("total.file.max.size");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(1024 * 500);
		File tempDirectory = new File(TEMP_DIR);
		factory.setRepository(tempDirectory);

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(Integer.parseInt(totalFileMaxSize));
		upload.setFileSizeMax(Integer.parseInt(fileMaxSize));
		
		return upload;
	}

}
