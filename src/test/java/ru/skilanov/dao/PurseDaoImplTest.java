package ru.skilanov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.skilanov.model.Currency;
import ru.skilanov.model.Purse;
import ru.skilanov.model.Role;
import ru.skilanov.model.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PurseDaoImplTest {

    private SessionFactory sessionFactory;
    private Session session;
    private PurseDao purseDao;

    @Before
    public void setUp() throws Exception {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        purseDao = new PurseDaoImpl(sessionFactory);
    }

    @Test
    public void whenInsertNewPurse_ThenItInserted() {
        Purse purse = new Purse(new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis())), new Currency("usd"), new BigDecimal(1),
                new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purse);

        assertNotNull(purseDao.getAll());
    }

    @Test
    public void whenGetAll_ThenHaveListOFPurses() {
        Purse purseSam = new Purse(new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis())), new Currency("usd"), new BigDecimal(1),
                new Timestamp(System.currentTimeMillis()));
        Purse purseMike = new Purse(new User("mike", "mike", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis())), new Currency("usd"), new BigDecimal(10),
                new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purseSam);
        purseDao.insert(purseMike);

        int size = purseDao.getAll().size();

        assertThat(size, is(2));
    }

    @Test
    public void whenDelete_ThenItDeleted() {
        Purse purseSam = new Purse(new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis())), new Currency("usd"), new BigDecimal(1),
                new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purseSam);

        purseDao.delete(1);

        assertNull(purseDao.findById(1));
    }

    @Test
    public void whenUpdate_ThenPurseUpdated() {
        Purse purseSam = new Purse(new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis())), new Currency("usd"), new BigDecimal(1.00),
                new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purseSam);

        User user = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));

        Purse targetPurse = purseDao.findById(1);
        targetPurse.setUser(user);
        purseDao.update(targetPurse);

        assertThat(purseDao.getAll().get(0).getUser(), is(user));
    }

    @Test
    public void whenFindById_ThenReturnRightResult() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));
        Currency rurCurrency = new Currency("rur");
        Purse purseRurSam = new Purse(userSam, rurCurrency, new BigDecimal(1.00),
                new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purseRurSam);

        int purseId = purseDao.findById(1).getId();

        assertThat(purseId, is(1));
    }

    @Test
    public void whenGetAllUserPurses_ThenReturnRightResult() {
        User userSam = new User("sam", "sam", "1", "mail", "moscow", Role.USER,
                new Timestamp(System.currentTimeMillis()));
        Currency rurCurrency = new Currency("rur");
        Currency usdCurrency = new Currency("usd");
        Purse purseRurSam = new Purse(userSam, rurCurrency, new BigDecimal(1.00),
                new Timestamp(System.currentTimeMillis()));
        Purse purseUsdSam = new Purse(userSam, usdCurrency, new BigDecimal(2.00),
                new Timestamp(System.currentTimeMillis()));

        purseDao.insert(purseRurSam);
        purseDao.insert(purseUsdSam);

        int size = purseDao.getAllUserPurses(1).size();

        assertThat(size, is(2));
    }
}
