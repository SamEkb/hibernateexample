package ru.skilanov.controller.users;

import org.junit.Before;
import org.junit.Test;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterUserServletTest {
    /**
     * JSP страница регистрации.
     */
    private static final String REGISTER = "WEB-INF/view/userRegister.jsp";
    /**
     * Сервлет регистрации.
     */
    private RegisterUserServlet registerUserServlet;
    /**
     * Ответ.
     */
    private HttpServletResponse response;
    /**
     * Запрос.
     */
    private HttpServletRequest request;
    /**
     * Дао пользователей.
     */
    private UserDaoImpl userDao;

    /**
     * перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        registerUserServlet = new RegisterUserServlet();
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        userDao = mock(UserDaoImpl.class);
    }

    /**
     * Тестирует метод doGet.
     *
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Test
    public void whenDoGet_ThenReturnRightPage() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(REGISTER)).thenReturn(dispatcher);

        registerUserServlet.doGet(request, response);
    }

    /**
     * Тестирует метод doPost.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoPost_ThenUserRegistered() throws IOException {
        when(request.getParameter(User.NAME)).thenReturn("name");
        when(request.getParameter(User.LOGIN)).thenReturn("login");
        when(request.getParameter(User.PASSWORD)).thenReturn("password");
        when(request.getParameter(User.ROLE)).thenReturn("role");
        when(request.getParameter(User.EMAIL)).thenReturn("email");

        registerUserServlet.setUserDao(userDao);
        registerUserServlet.doPost(request, response);
    }
}
