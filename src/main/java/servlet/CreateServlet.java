package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/create")
public class CreateServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        User user = new User(name, nickname, role, password);
        userService.addUser(user);
        response.sendRedirect(request.getContextPath() + "/admin");
    }
}