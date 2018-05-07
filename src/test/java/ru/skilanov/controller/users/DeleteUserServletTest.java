package ru.skilanov.controller.users;

import org.junit.Test;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс тестирует сервлет удаления пользователя.
 */
public class DeleteUserServletTest {

    /**
     * Тестирует метод doPost.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoPost_ThenReturnDeletedUser() throws IOException {
        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        UserDaoImpl userDao = mock(UserDaoImpl.class);

        when(request.getParameter(User.ID)).thenReturn("1");

        deleteUserServlet.setUserDao(userDao);
        deleteUserServlet.doPost(request, response);
    }
}
