package com.ltw.bt2308;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    // Thông tin đăng nhập mẫu (trong thực tế nên lưu trong database)
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Kiểm tra nếu đã đăng nhập thì chuyển đến dashboard
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userLoggedIn".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                    response.sendRedirect("dashboard");
                    return;
                }
            }
        }
        
        // Hiển thị form đăng nhập
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Đăng nhập</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        out.println(".login-container { max-width: 400px; margin: 50px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h2 { text-align: center; color: #333; margin-bottom: 30px; }");
        out.println(".form-group { margin-bottom: 20px; }");
        out.println("label { display: block; margin-bottom: 5px; color: #555; }");
        out.println("input[type=text], input[type=password] { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }");
        out.println("button { width: 100%; padding: 12px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }");
        out.println("button:hover { background-color: #45a049; }");
        out.println(".error { color: red; margin-bottom: 15px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='login-container'>");
        out.println("<h2>Đăng nhập hệ thống</h2>");
        
        // Hiển thị thông báo lỗi nếu có
        String error = request.getParameter("error");
        if (error != null) {
            out.println("<div class='error'>Tên đăng nhập hoặc mật khẩu không đúng!</div>");
        }
        
        out.println("<form method='post' action='login'>");
        out.println("<div class='form-group'>");
        out.println("<label for='username'>Tên đăng nhập:</label>");
        out.println("<input type='text' id='username' name='username' required>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label for='password'>Mật khẩu:</label>");
        out.println("<input type='password' id='password' name='password' required>");
        out.println("</div>");
        out.println("<button type='submit'>Đăng nhập</button>");
        out.println("</form>");
        out.println("<p style='text-align: center; margin-top: 20px; color: #666;'>");
        out.println("Tài khoản mẫu: admin / password123");
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Kiểm tra thông tin đăng nhập
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            // Tạo cookie để lưu trạng thái đăng nhập
            Cookie loginCookie = new Cookie("userLoggedIn", "true");
            loginCookie.setMaxAge(30 * 60); // Cookie có hiệu lực trong 30 phút
            loginCookie.setPath("/");
            response.addCookie(loginCookie);
            
            // Tạo cookie lưu tên người dùng
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(30 * 60);
            userCookie.setPath("/");
            response.addCookie(userCookie);
            
            // Chuyển hướng đến trang dashboard
            response.sendRedirect("dashboard");
        } else {
            // Đăng nhập thất bại, chuyển về trang đăng nhập với thông báo lỗi
            response.sendRedirect("login?error=1");
        }
    }
}
