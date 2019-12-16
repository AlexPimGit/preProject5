package servlet;

import model.User;
import service.UserHibernateServiceImpl;
import service.UserJdbcServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        User user = new User(name, nickname);
        UserHibernateServiceImpl.getInstance().addUser(user);
        //request.getRequestDispatcher("/index").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/");
    }
}