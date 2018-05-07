package ru.skilanov.controller.users;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс тестирует сервлет главной страницы.
 */
public class MainPageServletTest {
    /**
     * JSP главной страницы.
     */
    private static final String MAIN_PAGE = "/WEB-INF/view//index.jsp";

    /**
     * Тестирует метод doGet.
     *
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Test
    public void whenDoGet_ThenGetMainPage() throws ServletException, IOException {
        MainPageServlet mainServlet = new MainPageServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(MAIN_PAGE)).thenReturn(requestDispatcher);

        mainServlet.doGet(request, response);
    }
}
