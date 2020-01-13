package servlet;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/loginSuccess.jsp"})
public class AdminFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false); //берем текущую сессию, false - если сессия не существует, то ссылка на нее не будет получена
        boolean isLoggedIn = (session != null && session.getAttribute("loginUser") != null);//кто-то залогинился?
        String loginURI = httpRequest.getContextPath() + "/admin";//кто там? там админ?
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);//админ уже на странице? или там не админ? кто-то на странице
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");//страница login.jsp?
        User currentUser = (User) session.getAttribute("loginUser");//достаем от туда юзера

        //если это админ - переправляем его на страницу админа
        if (currentUser.getRole().equals("admin")) {
            httpRequest.getRequestDispatcher("/admin").forward(httpRequest, httpResponse);
        }
        // если он уже залогинился и пытается залогиниться еще раз
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            httpRequest.getRequestDispatcher("/admin").forward(httpRequest, httpResponse);
        }
        //залогинился не админ
        else if (isLoggedIn || isLoginRequest) {
            filterChain.doFilter(servletRequest, servletResponse);//передаем дальше в цепочку фильтров
        }
        //иначе, все шуруйте логиниться
        else {
            httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

/*
HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("adminUser") != null);

        String loginURI = httpRequest.getContextPath() + "/admin/login";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // the admin is already logged in and he's trying to login again
            // then forwards to the admin's homepage
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/admin/");
            dispatcher.forward(servletRequest, servletResponse);

        } else if (isLoggedIn || isLoginRequest) {
            // continues the filter chain
            // allows the request to reach the destination
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            // the admin is not logged in, so authentication is required
            // forwards to the Login page
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("login.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
 */