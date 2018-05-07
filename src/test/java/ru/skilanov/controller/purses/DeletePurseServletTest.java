package ru.skilanov.controller.purses;

import org.junit.Test;
import ru.skilanov.dao.PurseDaoImpl;
import ru.skilanov.model.Purse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс тестирует сервлет удаления кошелька.
 */
public class DeletePurseServletTest {

    /**
     * Тестирует doPost удаления кошелька.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoPost_ThenItDeleted() throws IOException {
        DeletePurseServlet deletePurseServlet = new DeletePurseServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PurseDaoImpl purseDao = mock(PurseDaoImpl.class);

        when(request.getParameter(Purse.ID)).thenReturn("1");

        deletePurseServlet.setPurseDao(purseDao);
        deletePurseServlet.doPost(request, response);
    }
}
