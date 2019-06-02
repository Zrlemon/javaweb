<%@page import="cn.edu.swu.domain.FileUploadBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="query.is" method="post">
          <input class="layui-input"  placeholder="文件名" name="file_name" >
          <input class="layui-input" placeholder="文件描述信息" name="file_desc" >
         <input type="submit" class="layui-btn"  lay-submit="" lay-filter="sreach" value="搜索"></input>
        </form>
      </div>
      <font color="red">${message }</font>
      <xblock>
            <form action="../fileuploadservlet2" method="post" enctype="multipart/form-data">
            <input type="file" name="file1"/>
      <!--  <button type="button" class="layui-btn" id="test1"><i class="layui-icon">&#xe67c;</i>上传文件</button>-->
     <input class="layui-input" type="text" name="desc1" placeholder="请输入学号，姓名，实验几,用逗号隔开" >
     <input class="layui-btn" type="submit" id="submit" value="上传"/>
        </form>
      </xblock>
           <%        	
            List<FileUploadBean> fileUploadBeans = (List<FileUploadBean>)request.getAttribute("fileUploadBeans");
           System.out.println(fileUploadBeans);
        	if(fileUploadBeans != null && fileUploadBeans.size()>0){
        	
        	%> 
          
      <table class="layui-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>文件名</th>
            <th>文件描述信息</th>
            <th>上传时间</th>
            </tr>
        </thead>
        <%
              for(FileUploadBean fileUploadBean: fileUploadBeans){
              String time = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(fileUploadBean.getDate());
              System.out.println(fileUploadBean.getFile_name());
               %>
        <tbody>
        <tr>

            	<td><%= fileUploadBean.getId() %></td>
            	<td><%= fileUploadBean.getFile_name() %></td>
            	<td><%= fileUploadBean.getFile_desc() %></td>
           		<td><%= time %></td>
            	<!--  <td class="td-manage">
             	 <a title="下载"  href="download.is?id=<%=fileUploadBean.getId()%>">
               	 	<i class="icon iconfont">&#xe714;</i>
              	 </a>
            	</td>
            	<td class="td-manage">
					<a titile="删除" href="delete.is?id=<%=fileUploadBean.getId()%>">
                			<i class="layui-icon">&#xe640;</i>
              		</a>
            	</td>-->
          		</tr>
        </tbody>
        <%} %>
      </table>
      <%} %>
      <div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div>

    </div>
    <script>
    layui.use('upload', function(){
    	  var upload = layui.upload;
    	   
    	  //执行实例
    	  var uploadInst = upload.render({
    	    elem: '#test1' //绑定元素
    	    ,url: '/upload/' //上传接口
    	    ,done: function(res){
    	      //上传完毕回调
    	    }
    	    ,error: function(){
    	      //请求异常回调
    	    }
    	  });
    	});


       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }
    </script>

  </body>

</html>