package cinemacct;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description: The logic of the program
 * @author Jorge
 */
public class Logic {
    
    private ArrayList<Movie> movies;
    private ArrayList<Payment> payments;
    private double price;
    private ArrayList<Order> orders;
    private RWFile rWFile;
    
    // Initialize, this method get everything from the files to save in the memory
    public void initialize() throws IncorrectDataException {
        
        // we create a class RWFile to read from the files
        rWFile = new RWFile();
        
        // we get the movies
        movies = rWFile.getMovies();
        // we get the payments
        payments = rWFile.getPayments();
        
        // class to compare payments by id
        IdPaymentCompare idPaymentCompare = new IdPaymentCompare();
        
        // i sort the arraylist payment to get the last id (the biggest one) from the list
        Collections.sort(payments, idPaymentCompare);
        
        // i set the last id + 1 to the atribute static of the class payment
        // to start to count from that id
        Payment.setIdCount(payments.get(payments.size() - 1).getId() + 1);
       
        // get price
        price = rWFile.getPrice();
        
        // get orders using movies and payments
        orders = rWFile.getOrdersByMoviesAndPayments(movies, payments);
        
        // class to o orders by id 
        IdOrderCompare idOrderCompare = new IdOrderCompare();
        
        
        // i sort the arraylist orders
        Collections.sort(orders, idOrderCompare);
        
        // i set the last id
        Order.setIdCount(orders.get(orders.size() - 1).getId() + 1);
      
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    
    // the payment is done
    public void payment(Order order, MethodPayment methodPayment) throws IOException {
        
        // create an object payment
        Payment payment =  new Payment(LocalDate.now(), LocalTime.now(), methodPayment, order.getTotalPriceWithDiscount());
        
        // assign the payment to the order
        order.addPayment(payment);
        
        // i add the order to arrayList
        orders.add(order);
        
        // i add the payment to the arrayList
        payments.add(payment);

        // write to the files
        rWFile.writePayment(payment);
        
        rWFile.WriteOrder(order);
        
    }
    
    // crete an order with a movie and ages
    public Order createOrder(ArrayList<Integer> ages, Movie movie) {
        
        return new Order(ages, this.price, movie);
        
    }
    
    
}
