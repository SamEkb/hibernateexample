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

/**
 * Фильтр аутентификации и авторизации.
 */
public class AuthenticationFilter implements Filter {
    /**
     * Атрибут.
     */
    private static final String USER = "user";
    /**
     * Фабрика сессий hibernate.
     */
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    /**
     * Метод инициализации.
     *
     * @param filterConfig FilterConfig
     * @throws ServletException ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Метод отвечает за аутентификацию и авторизацию пользователя, если пользователь открыл сеанс, то при попытке входа
     * в приложение будет отправлен на соответствующую страницу. Если не вошел, то будет открыт сеанс. Если такого
     * пользователя не существует, то он будет отправлен на страницу аутентификации.
     *
     * @param servletRequest  ServletRequest
     * @param servletResponse ServletResponse
     * @param filterChain     FilterChain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String login = request.getParameter(User.LOGIN);
        String password = request.getParameter(User.PASSWORD);

        UserDaoImpl userDao = new UserDaoImpl(factory);

        User user = userDao.finedByLoginAndPassword(login, password);

        if (nonNull(session) && session.getAttribute(USER) != null) {
            User userRole = (User) session.getAttribute(USER);

            moveToMenu(response, request, userRole.getRole());
        } else if (userDao.isUserExist(login, password)) {
            Role role = userDao.finedByLoginAndPassword(login, password).getRole();
            request.getSession().setAttribute(USER, user);

            moveToMenu(response, request, role);
        } else {
            moveToMenu(response, request, Role.UNKNOWN);
        }
    }

    /**
     * Метод навигации по страницам, в зависимости от прав пользователя.
     *
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     * @param role     Role
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
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
