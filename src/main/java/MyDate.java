package main.java;

import java.util.Scanner;

public class MyDate {
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static Scanner scanner = new Scanner(System.in);
    int year, month, day;
    String YEAR_MONTH_DAY;

    public MyDate(MyDate date) {
        this(date.YEAR_MONTH_DAY);
    }

    public MyDate(String YYYY_MM_DD) {
//      assigns respective year month and date
        YEAR_MONTH_DAY = YYYY_MM_DD;
        if (YEAR_MONTH_DAY.equalsIgnoreCase("NEVER_EXPIRES")) {
            year = 0;
            month = 0;
            day = 0;
            return;
        } else {
            year = Integer.parseInt(YEAR_MONTH_DAY.split("-")[0]);
            month = Integer.parseInt(YEAR_MONTH_DAY.split("-")[1]);
            day = Integer.parseInt(YEAR_MONTH_DAY.split("-")[2]);
        }
    }

    public MyDate(int year, int month, int day) {
        this(year + "-" + month + "-" + day);
    }

    public static String validateDateString(String dateString) {
        String[] arr = dateString.split("-");
        for (int i = 0; i < 3; i++) {
            for (int j = 'a'; j <= 'z'; j++) {
                if (arr[i].toLowerCase().contains((char) j + "")) {
                    System.out.println("INVALID DATE RE-ENTER in 'yyyy-mm-dd' format");
                    return validateDateString(scanner.nextLine());
                }
            }
        }
        return dateString;
    }

    public MyDate nextDay() {
        return validateDate(year, month, ++day);
    }

    private MyDate validateDate(int year, int month, int day) {
        if (validateDay(year, month, day).isValid())
            return validateDay(year, month, day);
        return this;
    }

    private boolean isValid() {
        return day <= daysInMonth[(month - 1) % 12] && month <= 12;
    }

    private MyDate validateMonth() {
        while (month > 12) {
            this.month -= 12;
            this.year++;
        }
        return new MyDate(year, month, day);
    }

    private MyDate validateDay(int year, int month, int day) {
        if (month == 2 && isLeapYear(year) && day > 29) {
            month++;
            day -= 29;

        } else if (day > daysInMonth[(month - 1) % 12]) {
            day %= daysInMonth[(month - 1) % 12];
            month++;
        }
        return new MyDate(year, month, day).validateMonth();
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    @Override
    public String toString() {
        return YEAR_MONTH_DAY;
    }

    public MyDate nextYear() {
        return new MyDate(++year, month, day);
    }

    public boolean greaterThan(MyDate secondDate) {
        if (this.year > secondDate.year)
            return true;
        if (this.month > secondDate.month)
            return true;
        if (this.day > secondDate.day)
            return true;
        return false;
    }
}

class MyDateDemo {
    public static void main(String[] args) {
        MyDate date = new MyDate("2016-12-31");
        for (int i = 0; i < 102; i++) {
            System.out.println(date);
            date = date.nextYear();
        }
    }
}