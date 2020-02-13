/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemacct;

import java.util.Comparator;

/**
 * Decription: to compares orders, and implement the interface comparator
 * @author Jorge
 */
public class IdOrderCompare implements Comparator<Order> {
    
    @Override
    public int compare(Order o1, Order o2) {
        
        if (o1.getId() < o2.getId())
            return -1;
        if (o1.getId() > o2.getId())
            return 1;
        else return 0;
        
    }    
     
}
