package com.ramos;
import java.lang.reflect.MalformedParametersException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTH_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        int principal = (int) readNumber("Principal", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Anuual Interest", 1, 30);
        byte year = (byte) readNumber("Period (year)", 1, 30);

        printMortgage(principal, annualInterest, year);

        printPaymentSchedule(principal, annualInterest, year);
    }

    private static void printMortgage(int principal, float annualInterest, byte year) {
        double mortgage = calculateMortage(principal, annualInterest, year);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte year) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(short month = 1; month <= year * MONTH_IN_YEAR; month++){
            double balance = mortgageSchedul(principal, annualInterest, year, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner in = new Scanner(System.in);
        double value;
        while(true){
            System.out.print(prompt + ": ");
            value = in.nextFloat();
            if(value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max +".");
        }
        return value;
    }

    public static double mortgageSchedul (int principal,
                                          float annualInterest,
                                          short years,
                                          short numberOfPaymentMade){
        float monthlyInterest = (annualInterest / PERCENT) / MONTH_IN_YEAR;
        short numberOfPayment = (short) (years * MONTH_IN_YEAR);

        double balance = principal
                *(Math.pow(1 + monthlyInterest, numberOfPayment) - Math.pow(1 + monthlyInterest, numberOfPaymentMade))
                /(Math.pow(1 + monthlyInterest, numberOfPayment) - 1);
        return balance;
    }

    public static double calculateMortage (int principal,
                                           float annualInterest,
                                           byte years) {

        float monthlyInterest = (annualInterest / PERCENT) / MONTH_IN_YEAR;
        short numberOfPayment = (short) (years * MONTH_IN_YEAR);

        double mortgage = principal
                * ((monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayment)
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1)));

        return mortgage;
    }


}
