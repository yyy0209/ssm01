<%--
  Created by IntelliJ IDEA.
  User: abd
  Date: 2019/1/8
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>登录界面</p>
    <form id="fm" action="doLogin.do" method="post">
        <input type="text" name="username"/><br>
        <input type="password" name="pwd"/><br>
        <input type="submit" value="login" />
    </form>
    <div>
        <a href="register">注册</a>
    </div>
</body>
</html>
