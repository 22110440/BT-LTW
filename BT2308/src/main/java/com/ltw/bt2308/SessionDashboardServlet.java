package com.ltw.bt2308;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/session-dashboard")
public class SessionDashboardServlet extends HttpServlet {
    
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
        
        // Tính thời gian session còn lại
        int maxInactiveInterval = session.getMaxInactiveInterval();
        int remainingTime = maxInactiveInterval;
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Session Dashboard</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        out.println(".dashboard-container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #333; margin-bottom: 30px; }");
        out.println(".welcome { background-color: #e3f2fd; padding: 20px; border-radius: 5px; margin-bottom: 30px; }");
        out.println(".menu { display: flex; gap: 15px; margin-bottom: 30px; flex-wrap: wrap; }");
        out.println(".menu a { padding: 10px 20px; background-color: #2196F3; color: white; text-decoration: none; border-radius: 4px; }");
        out.println(".menu a:hover { background-color: #1976D2; }");
        out.println(".logout { padding: 10px 20px; background-color: #f44336; color: white; text-decoration: none; border-radius: 4px; }");
        out.println(".logout:hover { background-color: #da190b; }");
        out.println(".content { background-color: #f9f9f9; padding: 20px; border-radius: 5px; }");
        out.println(".session-info { background-color: #fff3e0; padding: 15px; border-radius: 5px; margin-bottom: 20px; }");
        out.println(".session-info h3 { margin-top: 0; color: #e65100; }");
        out.println(".session-info p { margin: 5px 0; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='dashboard-container'>");
        out.println("<h1>Session Dashboard</h1>");
        
        out.println("<div class='welcome'>");
        out.println("<h2>Chào mừng, " + username + "!</h2>");
        out.println("<p>Bạn đã đăng nhập thành công vào hệ thống sử dụng Session.</p>");
        out.println("</div>");
        
        out.println("<div class='session-info'>");
        out.println("<h3>📊 Thông tin Session</h3>");
        out.println("<p><strong>Session ID:</strong> " + session.getId() + "</p>");
        out.println("<p><strong>Thời gian đăng nhập:</strong> " + loginTimeStr + "</p>");
        out.println("<p><strong>Thời gian timeout:</strong> " + (maxInactiveInterval / 60) + " phút</p>");
        out.println("<p><strong>Thời gian còn lại:</strong> " + (remainingTime / 60) + " phút</p>");
        out.println("</div>");
        
        out.println("<div class='menu'>");
        out.println("<a href='hello-servlet'>Hello Servlet</a>");
        out.println("<a href='session-protected/profile'>Hồ sơ cá nhân</a>");
        out.println("<a href='session-protected/settings'>Cài đặt</a>");
        out.println("<a href='session-logout' class='logout'>Đăng xuất</a>");
        out.println("</div>");
        
        out.println("<div class='content'>");
        out.println("<h3>Thông tin hệ thống</h3>");
        out.println("<p>Đây là trang dashboard được bảo vệ bởi Session authentication.</p>");
        out.println("<p><strong>Ưu điểm của Session:</strong></p>");
        out.println("<ul>");
        out.println("<li>Dữ liệu lưu trên server (an toàn hơn Cookie)</li>");
        out.println("<li>Không thể bị chỉnh sửa từ client</li>");
        out.println("<li>Tự động hết hạn khi đóng trình duyệt</li>");
        out.println("<li>Có thể lưu trữ nhiều thông tin phức tạp</li>");
        out.println("</ul>");
        out.println("<p>Bạn có thể truy cập các trang được bảo vệ khác trong thư mục /session-protected/</p>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
