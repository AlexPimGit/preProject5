package servlet.filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user")
public class UserFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(); //берем текущую сессию, false - если сессия не существует, то ссылка на нее не будет получена
        User currentUser = (User) session.getAttribute("loginUser");//достаем от туда юзера
        if (currentUser == null) {
            httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
            return;
        }
        if (currentUser.getName() == null || currentUser.getPassword() == null || currentUser.getRole() == null) {
            httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
            return;
        }
        if ("admin".equals(currentUser.getRole()) || "user".equals(currentUser.getRole())) {
            httpRequest.getRequestDispatcher("/user").forward(httpRequest, httpResponse);
        } else {
            httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
