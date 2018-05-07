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

/**
 * Класс тестирует сервлет создания пользователя.
 */
public class CreateUserServletTest {
    /**
     * JSP страница добавления пользователя.
     */
    private static final String ADD_USER = "WEB-INF/view/addUser.jsp";
    /**
     * Сервлет создания пользователя.
     */
    private CreateUserServlet createUserServlet;
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
        createUserServlet = new CreateUserServlet();
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
    public void whenDoGet_ThenReturnCreateUserPage() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(ADD_USER)).thenReturn(dispatcher);

        createUserServlet.doGet(request, response);
    }

    /**
     * Тестирует метод doPost.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoPost_ThenReturnAddedUser() throws IOException {
        when(request.getParameter(User.NAME)).thenReturn("name");
        when(request.getParameter(User.LOGIN)).thenReturn("login");
        when(request.getParameter(User.PASSWORD)).thenReturn("password");
        when(request.getParameter(User.EMAIL)).thenReturn("email");
        when(request.getParameter(User.CITY)).thenReturn("City");
        when(request.getParameter(User.ROLE)).thenReturn("admin");

        createUserServlet.setUserDao(userDao);
        createUserServlet.doPost(request, response);
    }
}
