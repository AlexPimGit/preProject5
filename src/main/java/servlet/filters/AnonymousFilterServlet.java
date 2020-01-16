package servlet.filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter("/")
public class AnonymousFilterServlet implements Filter {
    private Logger LOGGER = Logger.getLogger(AnonymousFilterServlet.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();

        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
            return;
        }
//        String loginUserName = null;
//        String loginUserPassword = null;
//        String loginUserRole = null;
//        try {
        String loginUserName = user.getName();
        String loginUserPassword = user.getPassword();
        String loginUserRole = user.getRole();
//        } catch (NullPointerException e) {
//            LOGGER.log(Level.WARNING, "Atas", e);
//        }

        if (loginUserName == null || loginUserPassword == null || loginUserRole == null) {
            httpRequest.getRequestDispatcher("/").forward(httpRequest, httpResponse);
            return;
        } else if ("admin".equals(loginUserRole)) {
            httpRequest.getRequestDispatcher("/admin").forward(httpRequest, httpResponse);
        } else if ("user".equals(loginUserRole)) {
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
