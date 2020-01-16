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

//@WebServlet("/user")
public class LogOutServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        session.removeAttribute("loginUserName", loginUserName);
//        session.setAttribute("loginUserPassword", loginUserPassword);
//        session.setAttribute("loginUserRole", loginUser.getRole());
//        session.setAttribute("loginUserNickname", loginUser.getNickname());

        getServletContext().getRequestDispatcher("/").forward(request, response);
    }
}


