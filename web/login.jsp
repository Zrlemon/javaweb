<%@page import="com.mysql.cj.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="${pageContext.request.contextPath}/images/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>

</head>
<body class="login-bg">

   
    <div class="login layui-anim layui-anim-up" >

        <div class="message">用户登录</div>
        <div id="darkbannerwrap"></div>
        
        <form  name="form" method="post" class="layui-form" >
            <input name="name" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="pass" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input type="text" value="" placeholder="请输入验证码（不区分大小写）" class ="input-val" style=" width: 234px;
        background: #ffffff;
        height: 40px;
        padding: 0 0%;
        border-radius: 5px;
        border: none;
        border: 1px solid rgba(0,0,0,.2);
        font-size: 14px;
        float: left">
        	<div style=" width: 100px; height: 40px;float: right">
            <canvas id="canvas" width="100" height="40"></canvas>
            </div>
            <hr class="hr15">
            <input class="btn"   value="登录"   style="width:100%;" type="submit">
             <hr class="hr20" >           
        </form>
    </div>

   <script>
 
    $(function(){
        var show_num = [];
        draw(show_num);
 
        $("#canvas").on('click',function(){
            draw(show_num);
        })
        $(".btn").on('click',function(){
            var val = $(".input-val").val().toLowerCase();
            var num = show_num.join("");

            if(val==''){
                alert('请输入验证码！');
            }else if(val == num){
                //alert('提交成功！');
            		login();
              
                //document.getElementByName("btnsubmit").action="login"
                //document.getElementByName("btnsubmit").submit();
                
                //$(".input-val").val('');
                //draw(show_num);
 
            }else{
                alert('验证码错误！请重新输入！');
                $(".input-val").val('');
                draw(show_num);
            }
            
        })
    })
  function login(){
        document.form.action="login"
        document.form.submit()
                }
 
    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度
        
        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180; //产生0~30之间的随机弧度
            var txt = aCode[j]; //得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20; //文字在canvas上的x坐标
            var y = 20 + Math.random() * 8; //文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";
 
            context.translate(x, y);
            context.rotate(deg);
 
            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);
 
            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }
 
    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
</script>

    <!-- 底部结束 -->

</body>
</html>