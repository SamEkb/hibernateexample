package ru.skilanov.model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Purse {
    private int id;
    private User user;
    private Currency currency;
    private BigDecimal amount;
    private Timestamp createDate;

    public Purse(User user, Currency currency, BigDecimal amount, Timestamp createDate) {
        this.user = user;
        this.currency = currency;
        this.amount = amount;
        this.createDate = createDate;
    }
}
