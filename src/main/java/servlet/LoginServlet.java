package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loginUserName = request.getParameter("name");
        String loginUserPassword = request.getParameter("password");
        User loginUser = userService.getUserByNamePassword(loginUserName, loginUserPassword);
        if (loginUser == null) {
            response.sendRedirect("/loginError.jsp");//
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            response.sendRedirect("/");
        }
    }
}

