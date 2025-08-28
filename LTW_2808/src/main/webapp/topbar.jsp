<%--
  Created by IntelliJ IDEA.
  User: Acer Nitro 5
  Date: 28/08/2025
  Time: 2:46 CH
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trang chờ</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.account == null}">
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập</a> |
                    <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                </li>
                <li><i class="fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="${pageContext.request.contextPath}/member/myaccount">
                            ${sessionScope.account.fullName}
                    </a> |
                    <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
                </li>
                <li><i class="fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
