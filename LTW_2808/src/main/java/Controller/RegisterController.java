package Controller;

import Model.User;
import Service.Impl.UserServiceImpl;
import Service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")

public class RegisterController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setRoleId(2); // mặc định USER
        user.setPhone(phone);
        user.setCreatedDate(new java.util.Date());

        boolean success = userService.register(user);
        if (success) {
            resp.getWriter().println("✅ Đăng ký thành công!");
        } else {
            resp.getWriter().println("❌ Đăng ký thất bại!");
        }
    }
}
