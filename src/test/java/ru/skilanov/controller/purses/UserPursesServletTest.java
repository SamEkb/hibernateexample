package ru.skilanov.controller.purses;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPursesServletTest {
    /**
     * JSP страница кошельков пользователя.
     */
    private static final String USER_PURSES = "/WEB-INF/view//userPurses.jsp";
    /**
     * Ответ.
     */
    private HttpServletResponse response;
    /**
     * Запрос.
     */
    private HttpServletRequest request;
    /**
     * Сервлет.
     */
    private UserPursesServlet userPursesServlet;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        userPursesServlet = new UserPursesServlet();
    }

    /**
     * Тестирования метода doGet.
     */
    @Test
    public void whenDoGet_ThenReturnUserPursesPage() {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(USER_PURSES)).thenReturn(dispatcher);
    }
}
