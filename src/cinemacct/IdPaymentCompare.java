/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.util.Comparator;

/**
 * Decription: to compares payments, and implement the interface comparator
 * @author Jorge
 */
public class IdPaymentCompare implements Comparator<Payment> {

    @Override
    public int compare(Payment p1, Payment p2) {
        
        if (p1.getId() < p2.getId())
            return -1;
        if (p1.getId() > p2.getId())
            return 1;
        else return 0;   
    }
    
    
    
    
}
