<%--
  Created by IntelliJ IDEA.
  User: abd
  Date: 2019/1/9
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        ul{
            list-style: none;
        }
        ul li{
            float: left;
            width: 30px;
            height: 30px;
            line-height: 30px;
            text-align:center;
            border:solid 1px #000000;
        }
        ul li a{
            text-decoration: none;  /*清除下划线*/
            color: #000000;
            display: block;
        }
        ul .page{
            width: 80px;
            height: 30px;
        }
        ul li:hover{
            cursor: pointer;  /*放上变手*/
        }
    </style>
</head>
<body>
<div>
<form>
    <input type="text" name="username"><input id="btn" type="submit" value="搜索">
</form>
<table>
    <thead>
        <tr>
            <th>id</th>
            <th>username</th>
            <th>password</th>
            <th>tele</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${lists}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.tele}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<ul>
    <c:choose>
        <c:when test="${page.pages>0}">
            <c:choose>
                <c:when test="${page.isFirstPage}">
                    <li class="page">首页</li>
                    <li class="page">上一页</li>
                </c:when>
                <c:otherwise>
                    <li class="page"><a href="list.do?pageNum=1${uname}">首页</a></li>
                    <li class="page"><a href="list.do?pageNum=${page.prePage}${uname}">上一页</a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${page.navigatepageNums}" var="i">
                <c:choose>
                    <c:when test="${page.pageNum==i}">
                        <li>${i}</li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="list.do?pageNum=${i}${uname}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${page.isLastPage}">
                    <li class="page">下一页</li>
                    <li class="page">尾页</li>
                </c:when>
                <c:otherwise>
                    <li class="page"><a href="list.do?pageNum=${page.nextPage}${uname}">下一页</a></li>
                    <li class="page"><a href="list.do?pageNum=${page.pages}${uname}">尾页</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
</ul>
<br>
<%--${lists}--%>
<%--${page}--%>
</body>
</html>
