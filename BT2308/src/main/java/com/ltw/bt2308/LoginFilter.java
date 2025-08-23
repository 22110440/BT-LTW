package com.ltw.bt2308;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard", "/protected/*"})
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Kiểm tra cookie đăng nhập
        Cookie[] cookies = httpRequest.getCookies();
        boolean isLoggedIn = false;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userLoggedIn".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                    isLoggedIn = true;
                    break;
                }
            }
        }
        
        if (isLoggedIn) {
            // Nếu đã đăng nhập, cho phép truy cập
            chain.doFilter(request, response);
        } else {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
    
    @Override
    public void destroy() {
        // Dọn dẹp filter
    }
}
