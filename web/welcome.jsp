<%@page import="cn.edu.swu.domain.FileUploadBean"%>
<%@page import="cn.edu.swu.dao.impl.UploadFileDao"%>
<%@page import="cn.edu.swu.dao.FileDAO"%>
<%@page import="cn.edu.swu.dao.impl.AdminDAOJdbcImpl"%>
<%@page import="cn.edu.swu.dao.AdminDAO"%>
<%@page import="cn.edu.swu.domain.Admin"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    </head>
    <body>
    <%
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(d);
		AdminDAO adminDao = new AdminDAOJdbcImpl();
		long coutadmin = adminDao.getCountWithCharacter("超级管理员");
		long coutuser = adminDao.getCountWithCharacter("普通用户");
		FileDAO fileDAO = new UploadFileDao();
		long coutfile = fileDAO.getCont("%%");
	%>
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">欢迎用户：
            <span class="x-red">admin</span>当前时间:<%= now %></blockquote>
        <fieldset class="layui-elem-field">
            <legend>数据统计</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>超级管理员数量</h3>
                                                <p>
                                                    <cite><%=coutadmin %></cite></p>
                                            </a>
                                        </li>
                                        
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>普通用户数量</h3>
                                                <p>
                                                    <cite><%=coutuser %></cite></p>
                                            </a>
                                        </li>
                                             <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>当前在线总人数</h3>
                                                <p>
                                                    <cite>${applicationScope.count1}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>文件数</h3>
                                                <p>
                                                    <cite><%=coutfile %></cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>更多内容持续更新</h3>
                                                <p>
                                                    <cite>zzZZ</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统通知</legend>
            <div class="layui-field-box">
                <table class="layui-table" lay-skin="line">
                    <tbody>
                        <tr>
                            <td >
                                <a class="x-a" href="#" target="_blank">实现的管理员的增删改查</a>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <a class="x-a" href="#" target="_blank">初步实现了session功能</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>系统版本</th>
                            <td>测试阶段</td></tr>
                        <tr>
                            <th>服务器地址</th>
                            <td>39.108.81.240</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>Ubuntu</td></tr>
                        <tr>
                            <th>运行环境</th>
                            <td>Apache/apache-tomcat-8.0.53</td></tr>
                        <tr>
                            <th>JAVA版本</th>
                            <td>openjdk 11.0.2 2019-01-15</td></tr>
                        <tr>
                            <th>服务器类型</th>
                            <td>阿里云服务器</td></tr>
                        <tr>
                            <th>MYSQL版本</th>
                            <td>Ver 14.14 Distrib 5.7.25</td></tr>
                        <tr>
                            <th>服务器内存</th>
                            <td>1G</td></tr>
                        <tr>
                            <th>上传附件大小限制</th>
                            <td>10M</td></tr>
                        <tr>
                            <th>上传文件格式限制</th>
                            <td>pptx,docx,doc,txt</td></tr>
                        <tr>
                            <th>剩余空间</th>
                            <td>8601.2M</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>开发团队</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>版权所有</th>
                            <td>
                                <a href="39.108.81.240:8080/myweb" class='x-a' target="_blank">访问官网</a></td>
                        </tr>
                        <tr>
                            <th>开发者</th>
                            <td>222016321072027</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <blockquote class="layui-elem-quote layui-quote-nm">本系统由x-admin提供技术支持,正处于开发阶段...</blockquote>
    </div>
    
    </body>
</html>