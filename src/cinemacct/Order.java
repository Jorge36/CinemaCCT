/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Description: Order and its attributes and behaviours
 * @author Jorge
 */
public class Order {

    private int id; 
    // total prive with discount
    private double totalPriceWithDiscount;
    private Movie movie;
    private ArrayList<Ticket> tickets = new ArrayList<>();
    private Payment payment;
    // attriutes to set id automatically
    private static int idCount = 1;
    // to format a dobule variable to 2 decimals
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void setIdCount(int idCount) {
        Order.idCount = idCount;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public Order(int id, double totalPriceWithDiscount) {
        this.id = id;
        this.totalPriceWithDiscount = totalPriceWithDiscount;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setTotalPriceWithDiscount(double totalPriceWithDiscount) {
        this.totalPriceWithDiscount = totalPriceWithDiscount;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    public double getTotalPriceWithDiscount() {
        return totalPriceWithDiscount;
    }

    public Payment getPayment() {
        return payment;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        df2.setRoundingMode(RoundingMode.DOWN);
        String order =  "Order" + " Id = " + id + System.lineSeparator() + 
                       "Total price with discount = " + df2.format(totalPriceWithDiscount) + System.lineSeparator() +
                       movie + System.lineSeparator() + System.lineSeparator() +
                       payment + System.lineSeparator() + System.lineSeparator();
        
        Iterator<Ticket> iter = tickets.iterator();
        // i travel through the ticket to print everyone       
        while (iter.hasNext()) {
            
            order = order + iter.next().toString();
            
            if (iter.hasNext())
                order = order + System.lineSeparator();
            
        }
        return order;
    }
        
    public Order(ArrayList<Integer> ages, double price, Movie movie) {
        
        this.id = idCount;
        Order.idCount++;
        this.movie = movie;
        this.totalPriceWithDiscount = 0.0d;
        
        int position = 1;
        Ticket ticket = null;
        // calculate the total price with discount and create every ticket
        for (Integer age: ages) {
            
            ticket = new Ticket(position,age, price);
            this.totalPriceWithDiscount = this.totalPriceWithDiscount + ticket.getPriceWithDiscount();
            this.tickets.add(ticket);
            position++;
        }
        
    }
        
    public void addPayment(Payment payment) {
        
        this.payment = payment;
        
    }
  
    // nested inner class to represent every line of a order,
    //  this class is used to save the age and the price
    public class Ticket {
    
        private int id;
        private int age;
        private double price;
        private double priceWithDiscount;

        public Ticket(int id, int age, double price, double priceWithDiscount) {
            this.id = id;
            this.age = age;
            this.price = price;
            this.priceWithDiscount = priceWithDiscount;
        }
        
        private Ticket(int id, int age, double price) {
            
            this.age = age;
            this.id = id;
            this.price = price;
            if (this.age <= 12)
                this.priceWithDiscount = (40 * this.price) / 100;
            else if (this.age >= 13 && this.age <= 64)
                this.priceWithDiscount = this.price;
            else if (this.age >= 65)
                this.priceWithDiscount = (20 * this.price) / 100;   
        }

        public int getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public double getPrice() {
            return price;
        }

        public double getPriceWithDiscount() {
            return priceWithDiscount;
        }

        @Override
        public String toString() {
            return "Ticket" + " Id = " + id + System.lineSeparator() +
                   "Age = " + age + System.lineSeparator() +
                   "Price = " + price + System.lineSeparator() + 
                   "Price with discount = " + priceWithDiscount + System.lineSeparator();
        }
                
    }
    
}
