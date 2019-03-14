package com.lunatech.kafka.common.java;

import java.math.BigDecimal;
import java.math.MathContext;

public class StockMessage {
    private String symbol;
    private BigDecimal value;

    public StockMessage(String symbol, BigDecimal value) {
        this.symbol = symbol;
        this.value = value.round(new MathContext(8));
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getValue() {
        return value;
    }
}
