package ru.skilanov.controller.users;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс тестирует сервлет выхода из сессии.
 */
public class LogoutServletTest {

    /**
     * Тестирует метод doGet.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoGet_ThenItLogout() throws IOException {
        LogoutServlet logoutServlet = new LogoutServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = request.getSession();
        HttpSession httpSession = mock(HttpSession.class);

        when(session).thenReturn(httpSession);

        logoutServlet.doGet(request, response);
    }
}
