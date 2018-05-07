package ru.skilanov.controller.currencies;

import org.junit.Test;
import ru.skilanov.dao.CurrencyDaoImpl;
import ru.skilanov.model.Currency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс тестирует сервлет удаления валюты.
 */
public class DeleteCurrencyServletTest {

    /**
     * Метод тестирует doPost удаления валюты.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoPost_ThenCurrencyDeleted() throws IOException {
        DeleteCurrencyServlet servlet = new DeleteCurrencyServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        CurrencyDaoImpl currencyDao = mock(CurrencyDaoImpl.class);

        when(request.getParameter(Currency.ID)).thenReturn("1");

        servlet.setCurrencyDao(currencyDao);

        servlet.doPost(request, response);
    }
}
