package com.ltw.bt2308;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/session-logout")
public class SessionLogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Lấy session hiện tại
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Xóa tất cả attributes trong session
            session.invalidate();
        }
        
        // Chuyển hướng về trang đăng nhập session
        response.sendRedirect("session-login");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
