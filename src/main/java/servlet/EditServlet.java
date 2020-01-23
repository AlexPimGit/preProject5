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

@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUserById(id);
            if (user != null) {
                request.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/notFound.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("/notFound.jsp").forward(request, response);//получать диспетчер из реквеста
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        User user = new User(id, name, nickname, role, password);
        userService.changeUser(user);
        response.sendRedirect("/admin");
    }
}

