<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abd
  Date: 2019/1/7
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--${user}--%>
<p>修改用户界面</p>
<%--<form action="doUpdateUser.do" method="post">&lt;%&ndash;设置地址栏不显示私有数据&ndash;%&gt;
    <input type="text" name="id" hidden="hidden" value="${user.id}">
    用户名称：<input type="text" name="username" value="${user.username}"><br>
    密码：<input type="text" name="password" value="${user.password}"><br>
    电话号码：<input type="text" name="tele" value="${user.tele}"><br>
    等级id：<input type="text" name="grade_id" value="${user.grade_id}"><br>
    <input type="submit" value="修改">
</form>--%>
<div>
    <form id="fo">
        <input type="text" name="id" id="uid" hidden="hidden" value="${user.id}">
        用户名：<input type="text" name="username" id="unames" class="ajaxCla" value="${user.username}"><span id="message"></span><br>
        密码：<input type="password" name="pwd" id="pwd" class="ajaxCla" value="${user.password}"><br>
        确认密码：<input type="password" name="pwds" id="pwds" class="ajaxCla" value="${user.password}"><span id="pmes"></span><br>
        电话：<input type="text" name="tele" id="tele" class="ajaxCla" value="${user.tele}"><br>
        <input type="button" value="修改" id="btn" disabled="disabled"><%--修改按钮名字--%>
    </form>
</div>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script>
    $(function () {
        //alert("测试");   //测试
        /*使用ajax*/
        var id = $("#uid").val();
        var un1 = $("#unames").val();
        var un;
        var pwd;
        var pwds;
        var tele;
        $(".ajaxCla").keyup(function () {  //获取标签对象，绑定键盘改变事件
            un = $("#unames").val(); //通过id获取这个标签对象,再获取这个对象的值
            pwd = $("#pwd").val();
            pwds = $("#pwds").val();
            tele = $("#tele").val();
            /*$("#message").text(un);  //获取标签对象，存放内容*/
            $.ajax({
                url:"verifyUpdateUser.do",   //没有提交也能够去java的这个DoRegisterServlet类
                type:"post",  //提交方式，在浏览器网址隐藏
                data:{"uame1":un1,"unames":un, "pwd":pwd, "pwds":pwds, "tele":tele},   //把值传入java代码中
                success:function (result) {  //回调函数，这里接受传出的数据
                    if(result == "0"){
                        $("#message").text("用户名不能为空");
                        $("#btn").attr("disabled",true);
                    }
                    if(result == "13"){
                        $("#message").text("可以修改");
                        $("#pmes").text("√");
                        //$("#btn").removeAttr("disabled","disabled");
                        $("#btn").attr("disabled",false);   //disabled属性不生效
                    }
                    if(result == "14"){
                        $("#message").text("可以修改");
                        $("#pmes").text("密码不一致");
                        $("#btn").attr("disabled",true);
                    }
                    if(result == "15"){
                        $("#message").text("可以修改");
                        $("#pmes").text("密码不能为空");
                        $("#btn").attr("disabled",true);
                    }
                    if (result == "2"){
                        $("#message").text("用户名以存在");
                        $("#btn").attr("disabled",true);
                    }
                }
            });
        });
        $("#btn").click(function () {     //获取标签对象，绑定点击事件
            $.ajax({
                url:"doUpdateUser.do",
                type:"post",
                data:{"id":id,"username":un, "password":pwd, "tele":tele},   //把值传入java代码中
                success:function (data) {
                    if (data=="1"){
                        alert("修改成功");
                        window.location.href="index.do";  //跳转界面
                    }
                    if (data=="0"){
                        alert("修改失败");
                    }
                }
            });
        });
    })
</script>
</body>
</html>
