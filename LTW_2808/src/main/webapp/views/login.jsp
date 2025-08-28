<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="username" placeholder="Tên đăng nhập" required />
        <input type="password" name="password" placeholder="Mật khẩu" required />
        <label><input type="checkbox" name="remember" /> Ghi nhớ đăng nhập</label>
        <button type="submit">Đăng nhập</button>
    </form>
    <div style="color:red;">
        ${alert}
    </div>
</body>
</html>
