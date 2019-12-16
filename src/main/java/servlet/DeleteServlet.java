package servlet;

import service.UserHibernateServiceImpl;
import service.UserJdbcServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        UserHibernateServiceImpl.getInstance().deleteUser(id);

        response.sendRedirect(request.getContextPath() + "/");
        //request.getRequestDispatcher("/").forward(request, response);

    }
}
