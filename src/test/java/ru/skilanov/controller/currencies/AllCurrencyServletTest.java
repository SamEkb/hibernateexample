package ru.skilanov.controller.currencies;

import org.junit.Test;
import ru.skilanov.dao.CurrencyDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс тестирует сервлет списка всех валют.
 */
public class AllCurrencyServletTest {

    /**
     * Jsp списка всех валют.
     */
    private static final String CURRENCIES_LIST = "WEB-INF/view/currencyList.jsp";

    /**
     * Тестирует doget списка валют.
     *
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Test
    public void whenDoGet_ThenReturnCurrenciesList() throws ServletException, IOException {
        AllCurrencyServlet servlet = new AllCurrencyServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        CurrencyDaoImpl currencyDao = mock(CurrencyDaoImpl.class);

        when(request.getRequestDispatcher(CURRENCIES_LIST)).thenReturn(dispatcher);
        servlet.setCurrencyDao(currencyDao);

        servlet.doGet(request, response);
    }
}
