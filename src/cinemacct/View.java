/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Description: Interaction with the user and print menu and elements on the screen
 * @author Jorge
 */
public class View {

    // Attribute global in to read from keyboard
    private Scanner in = new Scanner(System.in);
    // Attribute messages global which are print to the user
    private final String msgWelcome = "**** Welcome to Cinema CCT *****";
    private final String msgFirstQuestion = "What would you like to do?";
    private final String msgOption1 = "[1] Create Order";
    private final String msgOption2 = "[2] List the films that are currently being shown at the cinema";
    private final String msgOption3 = "[3] View previous orders";
    private final String msgOption4 = "[4] Quit the program";
    private final String msgChooseOption = "Choose an option [1-4]: ";
    private final String msgChooseFilm = "What film would you like to watch? ";
    private final String msgHowManyTickets = "How many ticket would you like to buy? ";
    private final String msgAges = "Please type the ages of the customers";
    private final String msgTypeAge = "Please enter the age: ";
    private final String msgChoosePaymentMethod = "How would you like to pay? ";  
    private final String msgProgramFinished = "The program has finished";
    private final String msgOrderCreatedSucc = "The order was created successfully";
    private final String msgTotalTransaction = "The total price of the transaction is: ";
    private final String msgInvalidValue = "You have entered an invalid input. Try again";
    private final String msgCloseProgram = "Are you sure you wish to close this program? (Y/N)";
    // Attribute to work with double variables, to print values of type double with only 2 decimals
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    // Close scanner
    public void CloseScanner() {
        
        in.close();
        
    }
    
    // method to show the main menu and interact with the user
    public int mainMenu(){
       
        // Variable choice to save the option which is typed by the user
        int choice;
        
        System.out.println(msgWelcome);
        System.out.println(msgFirstQuestion);
        
        do{
            // code to display all possible user choices
            System.out.println(msgOption1);
            System.out.println(msgOption2);
            System.out.println(msgOption3);
            System.out.println(msgOption4);
            
            try {
                // Scanner for user input
                choice = in.nextInt();  
                if(choice>=1 && choice<=4) {
                    return choice;
                }    
                else 
                    System.out.println(msgInvalidValue); 
                
                   
            } catch (InputMismatchException e) {
                in.next();
                System.out.println(msgInvalidValue);
            }
            
        }while(true); 

    }
 
   // method to ask if the user wants to close the program
   public boolean closeProgram() {
       
       char answer;
       int howMany = 1;

       System.out.print(msgCloseProgram);

       do {
           
            try {
                // Scanner for user input
                answer = in.next().charAt(0); 
                switch (answer) {
                    case 'Y':
                    case 'y':
                        return true;
                    case 'N':
                    case 'n':
                        return false;
                    default:
                        if (howMany > 3)
                            return false;
                        System.out.println(msgInvalidValue);
                        howMany++;
                        break;
                }
                                     
            } catch(InputMismatchException e) {

               // if the user fail to typed the value 4 times, the metohod return with value 0
               if (howMany > 3) 
                    return false;
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        } while(true);       
       
       
   }
    public void printOrderCreatedSuccessfully() {
        
        System.out.println(System.lineSeparator() + msgOrderCreatedSucc + System.lineSeparator());        
        
    }
    
    public void printTotalTransaction(double amount) {
     
        // Rounding to down
        df2.setRoundingMode(RoundingMode.DOWN);
        System.out.println(msgTotalTransaction + df2.format(amount));

    }
        
    public void printProgramFinished() {
        
        System.out.println(msgProgramFinished); 
        
    }
    
    // Method which allow user to choose a film
    public Movie chooseFilm(ArrayList<Movie> movies) {
        
       int choice;
       int howMany = 1;
       
       System.out.println(msgChooseFilm);

       do {
           
            try {
                 // code to display all possible user choices
                 printFilms(movies);
                 // Scanner for user input
                 choice = in.nextInt();  
                 // if the user type a valid value of movie
                 if(choice>=1 && choice<=movies.size()) 
                    return movies.get(choice-1); // return that movie
                 else {
                    // if the user fail to type the value 4 times, the metohod return with value null
                    if (howMany > 3) 
                        return null;
                        
                    System.out.println(msgInvalidValue);
                    howMany++;
                     
                 }
                     
            } catch(InputMismatchException e) {
                
               // to clear an invalid input from the Scanner
               in.next();
                // if the user fail to typed the value 4 times, the metohod return with value null
               if (howMany > 3) 
                    return null;
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        } while(true);
       
    }
    
    
    public void printFilms(ArrayList<Movie> movies) {

        int position = 1;
        
        for (Movie movie: movies) {
            
            System.out.println("[" + position + "] " + movie.toString() + System.lineSeparator());
            position++;
            
        }
                       
    }
    
    public void printOrders(ArrayList<Order> orders) {
        
        int position = 1;
 
        for (Order order: orders) {
            
            System.out.println("[" + position + "] " + order.toString() + System.lineSeparator());
            position++;
            
        }
            
    }
    
    public void printOrder(Order order) {
        
        System.out.println(order.toString());
        
    }
    
    // Method which allow user to choose the ager of the customers
    public ArrayList<Integer> getAges() {
        
       int howManyTicket;
       ArrayList<Integer> ages = null;
       int age;
       
       System.out.println(msgHowManyTickets);
       // to type how many ticket the user want to buy
       howManyTicket = getValueFromKeyboard();
       if (howManyTicket == 0)
           return ages;
       
       ages = new ArrayList<>();
       System.out.println(msgAges); 
       // depending of the vaiable howmany, this code ask user to type the ages
       for (int i=1; i <= howManyTicket; i++) {
           
            System.out.println(msgTypeAge);
            // to type the age of a customer
            age = getValueFromKeyboard();
            if (age == 0) {
                ages.clear();
                return null;
            }
            ages.add(age);
       }
       
       return ages;
        
    }

    // Method generic to ask user to enter a value
    // if the user fail to type a value 4 times, this mehod return with 0
    private int getValueFromKeyboard() {
 
       int choice;
       int howMany = 1;

       do {
           
            try {
                 // Scanner for user input
                 choice = in.nextInt();                      
                 return choice;                     
            } catch(InputMismatchException e) {
               // to clear an invalid input from the Scanner
               in.next();
               // if the user fail to typed the value 4 times, the metohod return with value 0
               if (howMany > 3) 
                    return 0;
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        } while(true);
        
    }
    
    // Method to ask user to enter a method of payment
    // if the user fail to type a value 4 times, this mehod return with ""
    public String chooseMethodPayment() {
                
       int choice;
       int howMany = 1;

       System.out.println(msgChoosePaymentMethod);
       printMethodPayments();
       
       do {
           
            try {
                 // Scanner for user input
                 choice = in.nextInt();
                 if (choice>=1 && choice<=MethodPayment.values().length) 
                    return MethodPayment.values()[choice-1].toString();      
                 else {
                    // if the user fail to typed the value 4 times, the metohod return with value ""
                    if (howMany > 3) 
                        return "";
                       
                    System.out.println(msgInvalidValue);
                    howMany++;
                     
                 }               
            } catch(InputMismatchException e) {
               // to clear an invalid input from the Scanner
               in.next();
               // if the user fail to typed the value 4 times, the metohod return with value ""
               if (howMany > 3) 
                    return "";
                        
               System.out.println(msgInvalidValue);
               howMany++;
            }
        
        } while(true);
         
    }
    
    public void printMethodPayments() {
        
        int position = 1;
        
        for (MethodPayment methodPayment: MethodPayment.values()) {
            
            System.out.println("[" + position + "] " + methodPayment);
            position++;
            
        }
        
    }

}
