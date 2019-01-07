<%--
  Created by IntelliJ IDEA.
  User: abd
  Date: 2019/1/7
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>添加界面</p><br>
<form action="doAddUser.do" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    电话号码：<input type="text" name="tele"><br>
    <input type="submit" value="添加">
</form>
</body>
</html>
