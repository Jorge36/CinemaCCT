/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Description: Class payment with its attribues and behaviour
 * @author Jorge
 */
public class Payment {
    
    private int id;
    private LocalDate dateOfPayment;
    private LocalTime TimeOfPayment;
    private MethodPayment methodPayment;
    private double amount;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    private static int idCount = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public LocalTime getTimeOfPayment() {
        return TimeOfPayment;
    }

    public void setTimeOfPayment(LocalTime TimeOfPayment) {
        this.TimeOfPayment = TimeOfPayment;
    }

    public MethodPayment getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(MethodPayment methodPayment) {
        this.methodPayment = methodPayment;
    }

    public static void setIdCount(int idCount) {
        Payment.idCount = idCount;
    }

    public Payment(LocalDate dateOfPayment, LocalTime TimeOfPayment, MethodPayment methodPayment, double amount) {
        this.id = Payment.idCount;
        Payment.idCount++;
        this.dateOfPayment = dateOfPayment;
        this.TimeOfPayment = TimeOfPayment;
        this.methodPayment = methodPayment;
        this.amount = amount;
    }

    public Payment(int id, LocalDate dateOfPayment, LocalTime TimeOfPayment, MethodPayment methodPayment, double amount) {
        this.id = id;
        this.dateOfPayment = dateOfPayment;
        this.TimeOfPayment = TimeOfPayment;
        this.methodPayment = methodPayment;
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        df2.setRoundingMode(RoundingMode.DOWN);
        return "Payment" + " Id =" + id + System.lineSeparator() + 
               "Date of Payment = " + dateOfPayment + " TimeOfPayment = " + TimeOfPayment + System.lineSeparator() +
               "Amount = " + df2.format(amount) + System.lineSeparator() +
               "Method of Payment = " + methodPayment;
    }
    
    
}
