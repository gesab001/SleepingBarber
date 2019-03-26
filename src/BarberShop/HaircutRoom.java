/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarberShop;

import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Giovanni Saberon
 */
public class HaircutRoom {
    Semaphore barber;
    Semaphore barber_chair;
    Customer customer;
    
    public HaircutRoom(Semaphore barber, Semaphore barber_chair){
        this.barber = barber;
        this.barber_chair = barber_chair;
//        this.customer = customer;
    }
    
    public void releaseBarberChair(){
        barber_chair.release();
        System.out.println("Barber chair is available");
    }
    
    public void occupyBarberChair(){
        try {
            barber_chair.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(HaircutRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Customer takes the barber chair");
    }
  

    
    
    
    
   
}
