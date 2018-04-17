package ru.skilanov.filter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AuthenticationFilter implements Filter {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDaoImpl userDao = new UserDaoImpl(factory);

        User user = userDao.finedByLogin(login, password);

        if (nonNull(session) && session.getAttribute("user") != null) {
            User userRole = (User) session.getAttribute("user");

            moveToMenu(response, request, userRole.getRole());
        } else if (userDao.isUserExist(login, password)) {
            Role role = userDao.finedByLogin(login, password).getRole();
            request.getSession().setAttribute("user", user);

            moveToMenu(response, request, role);
        } else {
            moveToMenu(response, request, Role.UNKNOWN);
        }
    }

    private void moveToMenu(HttpServletResponse response, HttpServletRequest request, Role role) throws ServletException, IOException {
        if (role.equals(Role.ADMIN)) {
            request.getRequestDispatcher("WEB-INF/view/adminPage.jsp").forward(request, response);
        } else if (role.equals(Role.USER)) {
            request.getRequestDispatcher("WEB-INF/view/userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        factory.close();
    }
}
