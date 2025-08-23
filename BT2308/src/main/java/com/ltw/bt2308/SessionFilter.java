package com.ltw.bt2308;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/session-dashboard", "/session-protected/*"})
public class SessionFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Kiểm tra session đăng nhập
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = false;
        
        if (session != null && session.getAttribute("userLoggedIn") != null) {
            isLoggedIn = true;
        }
        
        if (isLoggedIn) {
            // Nếu đã đăng nhập, cho phép truy cập
            chain.doFilter(request, response);
        } else {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập session
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/session-login");
        }
    }
    
    @Override
    public void destroy() {
        // Dọn dẹp filter
    }
}
