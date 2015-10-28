package com.project.util;

/**
 * Created by Максим on 26.10.2015.
 */
public class Date {
    int day;
    int month;
    int year;
    public Date(int day, int month, int year){ // инициализация даты

    }
    public static Date  parseToDate(String illegalDate){
        return new Date(0,0,0);
    };

    @Override//Формат даты
    public String toString() {
        return super.toString();
    }
}
