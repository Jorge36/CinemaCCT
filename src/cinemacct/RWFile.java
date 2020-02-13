/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Description: Read and Write from/to files in \\files\\
 * @author Jorge
 */
public class RWFile {
    
    // Attributes which have the paths of the different files
    private final String pathOfFileMovies = Paths.get(".") + "\\files\\movies.txt";
    private final String pathOfFileOrders = Paths.get(".") + "\\files\\orders.txt";
    private final String pathOfFilePrice = Paths.get(".") + "\\files\\price.txt";
    private final String pathOfFilePayments = Paths.get(".") + "\\files\\payment.txt";
    // Attribute with the separator used inside of the files to separate each register
    private final String separator = "\\|";
    
    // Get the movies from movies.txt
    // Structure of the file: Id|Title|Description|Year|Genre
    // The file finish at the last line, no new line is added
    public ArrayList<Movie> getMovies() throws IncorrectDataException {
          
        // Scanner to read from the file
        Scanner myScanner = null;
        
        // Line which has each register or a full line
        String lineOfFile;
        
        // String to save every field of a register
        String[] lineOfMovie;
        
        // Number of the line of the file which is used by the exception to show more details if there is an error 
        int numberOfLine = 1;
        
        Movie movie;

        ArrayList<Movie> movies = new ArrayList<>();
        
        try {
                        
            myScanner = new Scanner(new File(pathOfFileMovies));
            
            while (myScanner.hasNext()){
                
                // Save a register from the file
                lineOfFile = myScanner.nextLine();
                
                // Split the register to get each file and put them in each cell of this array
                lineOfMovie = lineOfFile.split(separator);
                
                // Create movie
                movie = new Movie(Integer.parseInt(lineOfMovie[0]), lineOfMovie[1], lineOfMovie[2], Integer.parseInt(lineOfMovie[3]), lineOfMovie[4]);
                
                movies.add(movie);
                
                numberOfLine++;
                
            }
                        
        } catch(FileNotFoundException | NumberFormatException e) {
            
            // Exception with the number of line, path plus name of the file and error message  
            throw new IncorrectDataException(numberOfLine, Paths.get(".").toAbsolutePath().toString().substring(0, Paths.get(".").toAbsolutePath().toString().length() - 2) + pathOfFileMovies.substring(2), e.getMessage());
            
            
        }
        finally {
            
            // Close scanner
            if (myScanner != null)
                myScanner.close();
        }
        
        return movies;
    }
    
    // Get the price from the file, I didnt add the currency but it is a important field which we need to have
    // Structure of the file: price
    // The file finish at the last line, no new line is added
    public double getPrice() throws IncorrectDataException {
        // Scanner to read from the file
        Scanner myScanner = null;
        double price = 	0.0d;
        // Number of the line of the file which is used by the exception to show more details if there is an error 
        int numberOfLine = 1;
        
        try {
            
            // scanner to read from the file
            myScanner = new Scanner(new File(pathOfFilePrice));
            
            if (myScanner.hasNext()) {
                
                price = Double.parseDouble(myScanner.next());
                
            }

        } catch(FileNotFoundException | NumberFormatException | NullPointerException e) {

            // Exception with the number of line, path plus name of the file and error message 
            throw new IncorrectDataException(numberOfLine, Paths.get(".").toAbsolutePath().toString().substring(0, Paths.get(".").toAbsolutePath().toString().length() - 2) + pathOfFilePrice.substring(2), e.getMessage());
             
        }
        finally {
            
            // close the scanner
            if (myScanner != null)
                myScanner.close();
        }
        
        return price;
    }

    // Get the payments from the file, I didnt add the currency but it is a important field which we need to have
    // Structure of the file: Id|Date of the payment|Time of the payment|method of payment|Amount
    // The file finish after the last line, a new empty line must be created
    public ArrayList<Payment> getPayments() throws IncorrectDataException {
       
        // Scanner to read from the file
        Scanner myScanner = null;
        
        String lineOfFile;
        
        String[] lineOfPayment;

        // Number of the line of the file which is used by the exception to show more details if there is an error 
        int numberOfLine = 1;
        
        Payment payment;

        ArrayList<Payment> payments = new ArrayList<>();
        
        try {
            // scanner to read from the file
            myScanner = new Scanner(new File(pathOfFilePayments));
            
            // Loop to travel through each register and create payments objects
            while (myScanner.hasNext()){
                
                lineOfFile = myScanner.nextLine();
                
                lineOfPayment = lineOfFile.split(separator);
                
                payment = new Payment(Integer.parseInt(lineOfPayment[0]), LocalDate.parse(lineOfPayment[1]), LocalTime.parse(lineOfPayment[2]), MethodPayment.valueOf(lineOfPayment[3]), Double.parseDouble(lineOfPayment[4]));
                
                payments.add(payment);
                
                numberOfLine++;
                
            }
                        
        } catch(FileNotFoundException | DateTimeException | IllegalArgumentException e) { // try NumberFormatException becayse IllegalArgument exception is bigger 
            // Exception with the number of line, path plus name of the file and error message            
            throw new IncorrectDataException(numberOfLine, Paths.get(".").toAbsolutePath().toString().substring(0, Paths.get(".").toAbsolutePath().toString().length() - 2) + pathOfFilePayments.substring(2), e.getMessage());
                    
        } 
        finally {
            // close scanner
            if (myScanner != null)
                myScanner.close();
        }
        
        return payments;        
           
    }

    // Get the orders from the file, I didnt add the currency but it is a important field which we need to have
    // Structure of the file: Id|Total with discount|Id ticket|Age|Price|price with discount|id Movie|id Payment
    // The file finish after the last line, a new empty line must be created    
    public ArrayList<Order> getOrdersByMoviesAndPayments(ArrayList<Movie> movies, ArrayList<Payment> payments) throws IncorrectDataException {
        
        // Scanner to read from the file
        Scanner myScanner = null;
        
        String lineOfFile;
        
        String[] lineOfOrder;

        // Number of the line of the file which is used by the exception to show more details if there is an error 
        int numberOfLine = 1;
        
        Order order = null;
        Order.Ticket ticket;

        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Order.Ticket> tickets = new ArrayList<>();
        
        try {
            // scanner to read from the file
            myScanner = new Scanner(new File(pathOfFileOrders));
            
            while (myScanner.hasNext()){
                
                // scanner read a register from the file
                lineOfFile = myScanner.nextLine();
                
                // Split the register in Id|Total with discount|Id ticket|Age|Price|price with discount|id Movie|id Payment
                lineOfOrder = lineOfFile.split(separator);
                

                if (!orders.isEmpty()) {
                    // If the list oders is empty and the last order is different from the current one
                    if (orders.get(orders.size() - 1).getId() != Integer.parseInt(lineOfOrder[0])) {
                         // add the ticket for the last order added to the arrayList orders
                         orders.get(orders.size() - 1).setTickets(tickets);
                         // create a new ArrayList object for the next order
                         tickets = new ArrayList<>();
                    }

                }
                
                // if order is null (first loop) and this method is reading the firt line of a new order 
                if (order == null || (order.getId() != Integer.parseInt(lineOfOrder[0]))) {
                     // we create a new order
                     order = new Order(Integer.parseInt(lineOfOrder[0]), Double.parseDouble(lineOfOrder[1]));
                     // we assign the payment and movie to this order
                     getMovieAndPayment(order, movies, payments, Integer.parseInt(lineOfOrder[6]), Integer.parseInt(lineOfOrder[7]));
                     // the order is added to the list order
                     orders.add(order);
                }
                
                // the ticket which is shown in this line is created                
                ticket = order.new Ticket(Integer.parseInt(lineOfOrder[2]), Integer.parseInt(lineOfOrder[3]), Double.parseDouble(lineOfOrder[4]), Double.parseDouble(lineOfOrder[5])); 
                
                // add the ticket to the list tickets
                tickets.add(ticket);
                
                // if it is the last register, we add the list ticket to the last order in the arrayList
                if (!myScanner.hasNext()) 
                    orders.get(orders.size() - 1).setTickets(tickets);

                numberOfLine++;
                
            }
                        
        } catch(FileNotFoundException | NumberFormatException e) {
            // Exception with the number of line, path plus name of the file and error message            
            throw new IncorrectDataException(numberOfLine, Paths.get(".").toAbsolutePath().toString().substring(0, Paths.get(".").toAbsolutePath().toString().length() - 2) + pathOfFileOrders.substring(2), e.getMessage());
            
            
        }
        finally {
            // close scanner
            if (myScanner != null)
                myScanner.close();
        }
        
        return orders;
        
    }
    
    // search for an order the movie and payment and these objects to the order
    private void getMovieAndPayment(Order order, ArrayList<Movie> movies, ArrayList<Payment> payments, int idMovie, int idPayment) {
        
        // We could do a binarysearch implementing class comparator to compare id of the objects
        // and use this one to call the methods sort and binarysearch of collections class
        // to do it more efficient, but in this case we dont have so much elememts to do this
        
        for (Movie movie: movies) {
            
            if (movie.getId() == idMovie) {
                
                order.setMovie(movie);
                break;
                
            }
                
        }

        for (Payment payment: payments) {
            
            if (payment.getId() == idPayment) {
                
                order.addPayment(payment);
                break;
                
            }
                
        }
        
    }
    
    // write the payment to the file
    public void writePayment(Payment payment) throws IOException {
         
        // we create a register with the separator
        String lineOfFile = payment.getId() + "|" + payment.getDateOfPayment() + "|" + payment.getTimeOfPayment() + "|" + payment.getMethodPayment() + "|" + payment.getAmount();
        
        // I create an object of type filewrite to write to the file
        FileWriter fileWriter = new FileWriter(pathOfFilePayments, true);
        
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        printWriter.println(lineOfFile);
        
        printWriter.close();                
        
    }
    
    // write the order to the file
    public void WriteOrder(Order order) throws IOException {
        
        String lineOfFile = "";
        ArrayList<Order.Ticket> tickets = order.getTickets();
        
        Iterator<Order.Ticket> iter = tickets.iterator();
        
        Order.Ticket ticket;
        // i create a line for each register   
        while (iter.hasNext()) {
                    
            ticket = iter.next();
            // we create a register with the separator 
            lineOfFile = lineOfFile + order.getId() + "|" + order.getTotalPriceWithDiscount() + "|" + ticket.getId() + "|" + ticket.getAge() + "|" + ticket.getPrice() + "|" + ticket.getPriceWithDiscount() + "|" + order.getMovie().getId() + "|" + order.getPayment().getId();
            
            if (iter.hasNext())
                lineOfFile = lineOfFile + System.lineSeparator();
        }

        // I create an object of type filewrite to write to the file
        FileWriter fileWriter = new FileWriter(pathOfFileOrders, true);
        
        PrintWriter printWriter =  new PrintWriter(fileWriter);
        
        printWriter.println(lineOfFile);
        
        printWriter.close();        
        
    }
    
    
    
    
    
}
