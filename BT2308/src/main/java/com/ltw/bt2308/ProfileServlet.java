package com.ltw.bt2308;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/protected/profile")
public class ProfileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Lấy tên người dùng từ cookie
        String username = "Người dùng";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hồ sơ cá nhân</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        out.println(".profile-container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #333; margin-bottom: 30px; }");
        out.println(".nav { margin-bottom: 30px; }");
        out.println(".nav a { padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px; margin-right: 10px; }");
        out.println(".nav a:hover { background-color: #45a049; }");
        out.println(".profile-info { background-color: #f9f9f9; padding: 20px; border-radius: 5px; }");
        out.println(".profile-info p { margin: 10px 0; }");
        out.println(".label { font-weight: bold; color: #555; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='profile-container'>");
        out.println("<h1>Hồ sơ cá nhân</h1>");
        
        out.println("<div class='nav'>");
        out.println("<a href='../dashboard'>← Quay lại Dashboard</a>");
        out.println("<a href='../logout'>Đăng xuất</a>");
        out.println("</div>");
        
        out.println("<div class='profile-info'>");
        out.println("<p><span class='label'>Tên đăng nhập:</span> " + username + "</p>");
        out.println("<p><span class='label'>Email:</span> " + username + "@example.com</p>");
        out.println("<p><span class='label'>Vai trò:</span> Quản trị viên</p>");
        out.println("<p><span class='label'>Ngày tham gia:</span> 01/01/2024</p>");
        out.println("<p><span class='label'>Trạng thái:</span> Hoạt động</p>");
        out.println("</div>");
        
        out.println("<p style='margin-top: 20px; color: #666;'>Đây là trang hồ sơ cá nhân được bảo vệ. Chỉ người dùng đã đăng nhập mới có thể truy cập.</p>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
