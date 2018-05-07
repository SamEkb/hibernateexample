package ru.skilanov.controller.currencies;

import org.junit.Before;
import org.junit.Test;
import ru.skilanov.dao.CurrencyDaoImpl;
import ru.skilanov.model.Currency;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateCurrencyServletTest {
    /**
     * JSP страница добавления валюты.
     */
    private static final String ADD_CURRENCY = "WEB-INF/view/addCurrency.jsp";
    /**
     * Http ответ.
     */
    private HttpServletResponse response;
    /**
     * Http запрос.
     */
    private HttpServletRequest request;
    /**
     * Сервлет создания валюты.
     */
    private CreateCurrencyServlet createCurrencyServlet;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        createCurrencyServlet = new CreateCurrencyServlet();
    }

    /**
     * Тестирует doGet получения страницы добавления валюты.
     *
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Test
    public void whenDoGet_ThenReturnAddCurrencyPage() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(ADD_CURRENCY)).thenReturn(dispatcher);
        createCurrencyServlet.doGet(request, response);
    }

    /**
     * Тестирует doPost добавления валюты.
     *
     * @throws IOException IOException
     */
    @Test
    public void whenDoPost_ThenCurrencyAdded() throws IOException {
        when(request.getParameter(Currency.NAME)).thenReturn("name");
        CurrencyDaoImpl currencyDao = mock(CurrencyDaoImpl.class);

        createCurrencyServlet.setCurrencyDao(currencyDao);
        createCurrencyServlet.doPost(request, response);
    }
}
