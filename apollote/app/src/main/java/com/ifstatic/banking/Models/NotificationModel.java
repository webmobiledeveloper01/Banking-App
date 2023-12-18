package com.ifstatic.banking.Models;

public class NotificationModel {

    private String date;

    private NotificationData notification;

    public NotificationModel(String date, NotificationData notification) {
        this.date = date;
        this.notification = notification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NotificationData getNotification() {
        return notification;
    }

    public void setNotification(NotificationData notification) {
        this.notification = notification;
    }
}
