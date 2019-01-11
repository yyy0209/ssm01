<%--
  Created by IntelliJ IDEA.
  User: abd
  Date: 2019/1/11
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<P>是否要删除${user.username}用户</P><br>
<form>
    <input type="text" name="id" id="uid" hidden="hidden" value="${user.id}">
    <input type="button" value="确定" id="btn">
    <input type="button" value="取消" id="noBtn">
</form>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script>
    $(function () {
        //alert("测试");
        $("#btn").click(function () {
            var id = $("#uid").val();
            $.ajax({
                url:"doDeleteUser.do",
                type:"post",
                data:{"id":id},
                success:function (result) {
                    if(result=="1"){
                        alert("删除成功");
                        window.location.href="index.do";  //跳转界面
                    }
                }
            })
        })
        $("#noBtn").click(function () {
            $.ajax({
                url:"noBtn.do",
                type:"post",
                success:function (result) {
                    if(result=="1"){
                        window.location.href="index.do";  //跳转界面
                    }
                }
            })
        })
    })
</script>
<%--${user}--%>
</body>
</html>
