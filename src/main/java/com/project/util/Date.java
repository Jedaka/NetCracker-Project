package com.project.util;

/**
 * Created by ������ on 26.10.2015.
 */
public class Date {
    int day;
    int month;
    int year;
    public Date(int day, int month, int year){ // ������������� ����

    }
    public static Date  parseToDate(String illegalDate){
        return new Date(0,0,0);
    };

    @Override//������ ����
    public String toString() {
        return super.toString();
    }
}
