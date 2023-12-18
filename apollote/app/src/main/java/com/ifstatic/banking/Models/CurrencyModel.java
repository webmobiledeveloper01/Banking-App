package com.ifstatic.banking.Models;

public class CurrencyModel {

    private String currencyName;

    private String currencySymbol;

    public CurrencyModel(String currencyName, String currencySymbol) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
