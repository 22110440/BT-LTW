<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ - Hệ thống đăng nhập</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        h1 { color: #333; text-align: center; margin-bottom: 40px; }
        .login-options { display: grid; grid-template-columns: 1fr 1fr; gap: 30px; margin-bottom: 40px; }
        .login-card { border: 2px solid #ddd; border-radius: 8px; padding: 25px; text-align: center; transition: all 0.3s ease; }
        .login-card:hover { transform: translateY(-5px); box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
        .cookie-card { border-color: #4CAF50; }
        .session-card { border-color: #2196F3; }
        .login-card h3 { margin-top: 0; margin-bottom: 15px; }
        .cookie-card h3 { color: #4CAF50; }
        .session-card h3 { color: #2196F3; }
        .login-btn { display: inline-block; padding: 15px 30px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; font-weight: bold; margin: 15px 0; transition: background-color 0.3s; }
        .login-btn:hover { background-color: #45a049; }
        .session-btn { background-color: #2196F3; }
        .session-btn:hover { background-color: #1976D2; }
        .features { text-align: left; margin: 20px 0; }
        .features ul { list-style: none; padding: 0; }
        .features li { padding: 8px 0; position: relative; padding-left: 25px; }
        .features li:before { content: "✓"; position: absolute; left: 0; color: #4CAF50; font-weight: bold; }
        .session-card .features li:before { color: #2196F3; }
        .pros-cons { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-top: 20px; }
        .pros, .cons { padding: 15px; border-radius: 5px; }
        .pros { background-color: #e8f5e8; }
        .cons { background-color: #ffebee; }
        .pros h4 { color: #2e7d32; margin-top: 0; }
        .cons h4 { color: #c62828; margin-top: 0; }
        .other-links { text-align: center; margin-top: 30px; }
        .other-links a { display: inline-block; padding: 10px 20px; background-color: #666; color: white; text-decoration: none; border-radius: 4px; margin: 0 10px; }
        .other-links a:hover { background-color: #555; }
        .credentials { background-color: #fff3e0; padding: 20px; border-radius: 5px; margin-top: 30px; text-align: center; }
        .credentials h3 { color: #e65100; margin-top: 0; }
    </style>
</head>
<body>
<div class="container">
    <h1>🔐 Hệ thống đăng nhập với Cookie và Session</h1>
    
    <div class="login-options">
        <!-- Cookie Authentication Card -->
        <div class="login-card cookie-card">
            <h3>🍪 Đăng nhập với Cookie</h3>
            <a href="login" class="login-btn">Đăng nhập với Cookie</a>
        </div>
        
        <!-- Session Authentication Card -->
        <div class="login-card session-card">
            <h3>🔒 Đăng nhập với Session</h3>

            <a href="session-login" class="login-btn session-btn">Đăng nhập với Session</a>
        </div>
    </div>
    
    <div class="credentials">
        <h3>🔑 Thông tin đăng nhập mẫu</h3>
        <p><strong>Tên đăng nhập:</strong> <code>admin</code></p>
        <p><strong>Mật khẩu:</strong> <code>password123</code></p>
        <p><em>Cookie và Session sẽ tự động hết hạn sau 30 phút</em></p>
    </div>
    
    <div class="other-links">
        <a href="hello-servlet">Hello Servlet</a>
        <a href="README.md">Xem hướng dẫn chi tiết</a>
    </div>
</div>
</body>
</html>