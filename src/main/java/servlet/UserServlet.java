package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        request.setAttribute("user", user);
//        request.setAttribute("loginUserNickname", user.getNickname());
//        request.setAttribute("loginUserPassword", user.getPassword());
//        request.setAttribute("loginUserRole", user.getRole());
        getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
    }
}
