/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Description: Main program
 * @author Jorge
 */
public class CinemaCCT {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // create an object logic 
        Logic logic = new Logic();
        
        try {
            // read from files and add the objects to the arrayList
            logic.initialize();
            
        } catch(IncorrectDataException e) {
            // if there is an error this custom exception will show user the error with the details
            System.out.println(e.getMessage());
            return;
            
        }

        View view = new View();
        int option;
        Movie movie;
        Order order;
        ArrayList<Integer> ages;
        MethodPayment methodPayment;
        String method;
        
        while(true) {
            // call the mainMenu to get the value chosen by the user
            option = view.mainMenu();
            switch(option) {
                
                case 1: // Create an order
                        // the user choose a film
                        movie = view.chooseFilm(logic.getMovies());
                        if (movie == null)
                            continue;
                        // the user types the ages
                        ages = view.getAges();
                        if (ages == null)
                            continue;
                        // I create an order with the ages and movie
                        order = logic.createOrder(ages, movie);
                        // show the total
                        view.printTotalTransaction(order.getTotalPriceWithDiscount());
                        // the user chose a method of payment
                        method = view.chooseMethodPayment();
                        if (method.equals(""))
                            continue;
                        // convert the string to a value of a MethodPayment enum
                        methodPayment = MethodPayment.valueOf(method);
                        try { 
                            // the payment is done
                            logic.payment(order, methodPayment);
                            view.printOrderCreatedSuccessfully();
                            // print the order
                            view.printOrder(order);
                        } catch(IOException e) {
                            
                            System.out.println(e.getMessage());
                            view.printProgramFinished();
                            return;
                        }
                        break;
                case 2: //print movies
                        view.printFilms(logic.getMovies());
                        break;
                case 3: // print the orders
                        view.printOrders(logic.getOrders());
                        break;
                case 4: // exit
                        if (view.closeProgram()) { // the program ask if the user want to exit (Y/N)
                            view.printProgramFinished();
                            view.CloseScanner(); // close scanner, there is a attribute called in of type scanner
                                                // which is used by view to interact with the user
                            return;
                        }

            }
            
        }

    }
    
}
