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
 * @author 14400
 */
public class WaitingRoom {
    Semaphore waiting_chairs;
    
    public WaitingRoom(Semaphore waiting_chairs){
       this.waiting_chairs = waiting_chairs;
    }
    
    public void seatOccupied(){
        try {
            waiting_chairs.acquire();
            System.out.println("Waiting seat is taken");
        } catch (InterruptedException ex) {
            Logger.getLogger(WaitingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean aCustomerIsWaiting(){
        int aCustomerIsWaiting= 4-waiting_chairs.availablePermits();
        if (aCustomerIsWaiting>0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isWaitingRoomFull(){
        int seatsAvailable = this.waiting_chairs.availablePermits();
        if (seatsAvailable==0){
            return true;
        }
        else{
            return false;
        }
    }
    public void seatAvailable(){
        waiting_chairs.release();
        System.out.println("Waiting seat is available");

    }
}

