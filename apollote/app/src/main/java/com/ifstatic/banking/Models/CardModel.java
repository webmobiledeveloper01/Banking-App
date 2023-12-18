package com.ifstatic.banking.Models;

public class CardModel {

    private String name;

    private String cardNo;

    private String expiry;

    public CardModel(String name, String cardNo, String expiry) {
        this.name = name;
        this.cardNo = cardNo;
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
