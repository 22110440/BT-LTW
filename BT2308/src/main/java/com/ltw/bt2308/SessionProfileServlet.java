package com.ltw.bt2308;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/session-protected/profile")
public class SessionProfileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Lấy thông tin từ session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        Long loginTime = (Long) session.getAttribute("loginTime");
        
        // Format thời gian đăng nhập
        String loginTimeStr = "Không xác định";
        if (loginTime != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            loginTimeStr = sdf.format(new Date(loginTime));
        }
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hồ sơ cá nhân - Session</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        out.println(".profile-container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #333; margin-bottom: 30px; }");
        out.println(".nav { margin-bottom: 30px; }");
        out.println(".nav a { padding: 10px 20px; background-color: #2196F3; color: white; text-decoration: none; border-radius: 4px; margin-right: 10px; }");
        out.println(".nav a:hover { background-color: #1976D2; }");
        out.println(".profile-info { background-color: #f9f9f9; padding: 20px; border-radius: 5px; margin-bottom: 20px; }");
        out.println(".profile-info p { margin: 10px 0; }");
        out.println(".label { font-weight: bold; color: #555; }");
        out.println(".session-details { background-color: #e3f2fd; padding: 15px; border-radius: 5px; margin-bottom: 20px; }");
        out.println(".session-details h3 { margin-top: 0; color: #1976D2; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='profile-container'>");
        out.println("<h1>Hồ sơ cá nhân - Session</h1>");
        
        out.println("<div class='nav'>");
        out.println("<a href='../session-dashboard'>← Quay lại Dashboard</a>");
        out.println("<a href='../session-logout'>Đăng xuất</a>");
        out.println("</div>");
        
        out.println("<div class='session-details'>");
        out.println("<h3>🔐 Thông tin Session</h3>");
        out.println("<p><span class='label'>Session ID:</span> " + session.getId() + "</p>");
        out.println("<p><span class='label'>Thời gian đăng nhập:</span> " + loginTimeStr + "</p>");
        out.println("<p><span class='label'>Thời gian timeout:</span> " + (session.getMaxInactiveInterval() / 60) + " phút</p>");
        out.println("</div>");
        
        out.println("<div class='profile-info'>");
        out.println("<h3>👤 Thông tin cá nhân</h3>");
        out.println("<p><span class='label'>Tên đăng nhập:</span> " + username + "</p>");
        out.println("<p><span class='label'>Email:</span> " + username + "@example.com</p>");
        out.println("<p><span class='label'>Vai trò:</span> Quản trị viên</p>");
        out.println("<p><span class='label'>Ngày tham gia:</span> 01/01/2024</p>");
        out.println("<p><span class='label'>Trạng thái:</span> Hoạt động</p>");
        out.println("<p><span class='label'>Phương thức xác thực:</span> Session</p>");
        out.println("</div>");
        
        out.println("<p style='margin-top: 20px; color: #666;'>");
        out.println("Đây là trang hồ sơ cá nhân được bảo vệ bởi Session authentication. ");
        out.println("Dữ liệu được lưu trữ an toàn trên server và không thể bị chỉnh sửa từ client.");
        out.println("</p>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
