<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Đăng ký tài khoản</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container mt-5">

<form action="${pageContext.request.contextPath}/register" method="post" class="p-4 border rounded bg-light">
  <h2 class="mb-4">Tạo tài khoản mới</h2>

  <div class="form-group">
    <label for="username">Tên tài khoản</label>
    <input type="text" id="username" name="username" class="form-control" placeholder="Nhập tên tài khoản" required>
  </div>

  <div class="form-group">
    <label for="password">Mật khẩu</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Nhập mật khẩu" required>
  </div>

  <div class="form-group">
    <label for="email">Email</label>
    <input type="email" id="email" name="email" class="form-control" placeholder="Nhập email" required>
  </div>

  <div class="form-group">
    <label for="fullName">Họ và tên</label>
    <input type="text" id="fullName" name="fullName" class="form-control" placeholder="Nhập họ và tên" required>
  </div>

  <div class="form-group">
    <label for="phone">Số điện thoại</label>
    <input type="text" id="phone" name="phone" class="form-control" placeholder="Nhập số điện thoại">
  </div>

  <button type="submit" class="btn btn-primary">Đăng ký</button>
  <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-secondary">Quay lại đăng nhập</a>
</form>

</body>
</html>
