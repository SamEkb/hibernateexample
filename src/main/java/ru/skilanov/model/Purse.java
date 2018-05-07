package ru.skilanov.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Сущность кошелька.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Purse {

    public static final String ID = "id";
    public static final String USER = "user";
    public static final String CURRENCY = "currency";
    public static final String AMOUNT = "amount";
    public static final String CREATE_DATE = "createDate";

    /**
     * id.
     */
    private int id;
    /**
     * Владелец кошелька.
     */
    private User user;
    /**
     * Валюта кошелька.
     */
    private Currency currency;
    /**
     * Сумма кошелька.
     */
    private BigDecimal amount;
    /**
     * Дата создания.
     */
    private Timestamp createDate;

    /**
     * Перегрузка конструктора.
     *
     * @param user       User
     * @param currency   Currency
     * @param amount     BigDecimal
     * @param createDate Timestamp
     */
    public Purse(User user, Currency currency, BigDecimal amount, Timestamp createDate) {
        this.user = user;
        this.currency = currency;
        this.amount = amount;
        this.createDate = createDate;
    }
}
