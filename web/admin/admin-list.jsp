<%@page import="cn.edu.swu.servlet.AdminServlet"%>
<%@page import="cn.edu.swu.domain.Admin"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="${pageContext.request.contextPath}/image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
  	<%
		/*Cookie[] cookies = request.getCookies();
		boolean islogin = false;
		for(Cookie cookie:cookies){
    		if(cookie.getName().equals("user")){
        		islogin=true;
   			 }
		}
		if(islogin==false){
   			 response.sendRedirect("login.jsp");
			}*/
	%>
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
        <form class="layui-form layui-col-md12 x-so" action="query.do" method="post">
          <input class="layui-input" placeholder="name" name="name" >
          <input class="layui-input" placeholder="phone" name="phone">
          <input name="mail"  placeholder="mail" autocomplete="off" class="layui-input">
          <input type="submit" class="layui-btn"  lay-submit="" lay-filter="sreach" value="查询"></input>
        </form>
      </div>
      
       <xblock>
        <!--  <button class="layui-btn" ><i class="layui-icon"></i>查询</button>-->
        <button class="layui-btn" onclick="x_admin_show('添加用户','${pageContext.request.contextPath}/admin/admin-add.jsp')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      </div>
      <% 
        	List<Admin> admins = (List<Admin>)request.getAttribute("admins");
//      		System.out.println(admins);
        	if(admins != null && admins.size()>0){
 //       		System.out.print(1);
        %>
        <table class="layui-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>登录名</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>角色</th>
            <th>加入时间</th>
            <th>编辑</th>
            <th>删除</th>
            </tr>
        </thead>
               <%
                 for(Admin admin: admins){

                     String time = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(admin.getDate());
                    //System.out.println(time);
               %>
         <tbody>
 			<tr>

            	<td><%= admin.getId() %></td>
            	<td><%= admin.getName() %></td>
            	<td><%= admin.getPhone() %></td>
            	<td><%= admin.getMail()%></td>
            	<td><%= admin.getCharacters() %></td>
           		<td><%= time %></td>
            	<td class="td-manage">
                	<a title="编辑"   href="edit.do?id=<%=admin.getId()%>">
                		<i class="layui-icon">&#xe642;</i>
              		</a>
            		<td class="td-manage">
						<!--               <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                		<i class="layui-icon">&#xe601;</i>
              			</a> -->
              			<a titile="删除" href="delete.do?id=<%=admin.getId()%>">
                			<i class="layui-icon">&#xe640;</i>
              			</a>

            	</td>
          		</tr>
          		
        	</tbody>
        	<% } %>
     		
      	</table>
   
    	<%
    		}
    	 %>



      <!--  <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>登录名</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>角色</th>
            <th>加入时间</th>
            <th>编辑</th>
            <th>删除</th>
        </thead>-->
       
      <div class="page">
 <div  id="test"></div>
      </div>
      
    <script>
  
    layui.use('laypage', function(){
      var laypage = layui.laypage;
      
      //执行一个laypage实例
      laypage.render({
        elem: 'test' //注意，这里的 test1 是 ID，不用加 # 号
        ,count: 50 //数据总数，从服务端得到
      });
    });
     /*layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });*/

       /*用户-停用*/
      /*function member_stop(obj,id){
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
      }*/

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
          	//document.getElementById("delete").action="delete.do"
            //document.getElementById("delete").submit();
          	window.location.href="delete.do" + '?id='+id;
              //发异步删除数据
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }



      /*function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }*/
    </script>

  </body>

</html>