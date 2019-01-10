<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abd
  Date: 2019/1/7
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border: solid 1px #000000;
            border-collapse: collapse;
        }
        table thead tr th,table tbody tr td{
            border: solid 1px #000000;
            padding: 5px 10px;
        }
        table tbody tr:nth-child(odd){
            background-color: blanchedalmond;
        }
    </style>
</head>
<body>
<%--${lists}--%>
<p>显示内容界面</p>
<a href="addUser.do">添加</a>
<a href="exit.do">退出</a>
<a href="upload.do">图片上传</a>
<a href="list.do">分页展示和模糊查询</a>
<table>
    <thead>
    <tr>
        <th>用户编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th>电话号码</th>
        <th>等级编号</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lists}" var="list">  <%--循环--%>
        <tr>
            <td>${list.id}</td>  <%--这个地方是通过get方法获取--%>
            <td>${list.username}</td>
            <td>${list.password}</td>
            <td>${list.tele}</td>
            <td>${list.grade_id}</td>
            <td><a href="deleteUser.do?id=${list.id}">删除</a>&nbsp;|&nbsp;<a href="updateUser.do?id=${list.id}">修改</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
