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

/**
 * Класс тестирует сервлет изменения валюты.
 */
public class UpdateCurrencyServletTest {
    /**
     * JSP страница обновления валюты.
     */
    private static final String UPDATE_CURRENCY = "/WEB-INF/view//updateCurrency.jsp";
    /**
     * Сервлет.
     */
    private UpdateCurrencyServlet updateCurrencyServlet;
    /**
     * Ответ.
     */
    private HttpServletResponse response;
    /**
     * Запрос.
     */
    private HttpServletRequest request;
    /**
     * Дао валюты.
     */
    private CurrencyDaoImpl currencyDao;

    /**
     * Перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        updateCurrencyServlet = new UpdateCurrencyServlet();
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        currencyDao = mock(CurrencyDaoImpl.class);
    }

    /**
     * Тестирует doGet получение страницы обновления валюты.
     *
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Test
    public void whenDoGet_ThenReturnUpdateCurrencyPage() throws ServletException, IOException {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter(Currency.ID)).thenReturn("1");
        when(request.getRequestDispatcher(UPDATE_CURRENCY)).thenReturn(dispatcher);

        updateCurrencyServlet.setCurrencyDao(currencyDao);
        updateCurrencyServlet.doGet(request, response);
    }
}
