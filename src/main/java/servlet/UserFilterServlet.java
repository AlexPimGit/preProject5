package servlet;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/loginSuccess.jsp"})
public class UserFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/admin/*")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("loginUser") != null);
        String loginURI = httpRequest.getContextPath() + "/user.jsp";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("user.jsp");

        User currentUser = (User) session.getAttribute("loginUser");//достаем от туда юзера
        if (currentUser.getRole().equals("user")) {
            httpRequest.getRequestDispatcher("/user.jsp").forward(httpRequest, httpResponse);
        }
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            httpRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);//передаем дальше в цепочку фильтров
        }
    }

    @Override
    public void destroy() {
    }
}
