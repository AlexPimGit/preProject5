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
            getServletContext().getRequestDispatcher("/loginError.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);//сохраняю в текущей сессии объект loginUser под именем "loginUser"
            response.sendRedirect("/loginSuccess.jsp");
        }
    }
}

/*
@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getService();
    private String adminRole = "admin";
    private String userRole = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //сохранить юзера в сессию
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (userService.checkUserPassword(name, password)) {
            if (userService.getUserByName(name).getRole().equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin");
            }
            if (userService.getUserByName(name).getRole().equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/user.jsp");
            }
        } else {
            //response.sendRedirect(request.getContextPath() + "/loginError.jsp");
            getServletContext().getRequestDispatcher("/loginError.jsp").forward(request, response);
        }
    }
}
 */
