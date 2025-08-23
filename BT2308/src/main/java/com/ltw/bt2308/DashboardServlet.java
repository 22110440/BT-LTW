package com.ltw.bt2308;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    
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
        out.println("<title>Dashboard</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        out.println(".dashboard-container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #333; margin-bottom: 30px; }");
        out.println(".welcome { background-color: #e8f5e8; padding: 20px; border-radius: 5px; margin-bottom: 30px; }");
        out.println(".menu { display: flex; gap: 15px; margin-bottom: 30px; }");
        out.println(".menu a { padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px; }");
        out.println(".menu a:hover { background-color: #45a049; }");
        out.println(".logout { padding: 10px 20px; background-color: #f44336; color: white; text-decoration: none; border-radius: 4px; }");
        out.println(".logout:hover { background-color: #da190b; }");
        out.println(".content { background-color: #f9f9f9; padding: 20px; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='dashboard-container'>");
        out.println("<h1>Dashboard</h1>");
        
        out.println("<div class='welcome'>");
        out.println("<h2>Chào mừng, " + username + "!</h2>");
        out.println("<p>Bạn đã đăng nhập thành công vào hệ thống.</p>");
        out.println("</div>");
        
        out.println("<div class='menu'>");
        out.println("<a href='hello-servlet'>Hello Servlet</a>");
        out.println("<a href='protected/profile'>Hồ sơ cá nhân</a>");
        out.println("<a href='protected/settings'>Cài đặt</a>");
        out.println("<a href='logout' class='logout'>Đăng xuất</a>");
        out.println("</div>");
        
        out.println("<div class='content'>");
        out.println("<h3>Thông tin hệ thống</h3>");
        out.println("<p>Đây là trang dashboard được bảo vệ bởi cookie authentication.</p>");
        out.println("<p>Cookie sẽ tự động hết hạn sau 30 phút.</p>");
        out.println("<p>Bạn có thể truy cập các trang được bảo vệ khác trong thư mục /protected/</p>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
