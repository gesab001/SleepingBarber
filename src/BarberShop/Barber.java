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
public class Barber implements Runnable{
    Semaphore barber;
    WaitingRoom waitingRoom;
    HaircutRoom haircutRoom;
    
    public Barber(Semaphore barber, WaitingRoom waitingRoom, HaircutRoom haircutRoom){
        this.barber = barber;
        this.waitingRoom = waitingRoom;
        this.haircutRoom = haircutRoom;
    }
 
    public void barberSleeps(){
        barber.release();
        haircutRoom.releaseBarberChair();
        System.out.println("Barber is sleeping");
    }
   
    public boolean isSleeping(){
        int sleeping = this.barber.availablePermits();
        if (sleeping==1){
            return true;
        }else{
            return false;
        }
    }
    public boolean notBusy(){
        int notbusy = this.barber.availablePermits();
        if (notbusy!=0){
            return true;
        }else{
            return false;
        }
    }
    public boolean barberChecksForWaitingCustomers(){
        boolean isWaiting = waitingRoom.aCustomerIsWaiting();
        try {
            barber.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Barber.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Barber checks for waiting customers");
        return isWaiting;
    }
    
    public void barberCutsHair(){
        
        if(barberChecksForWaitingCustomers() || this.notBusy() ){
            try {
                this.barber.release();
                this.barber.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barber.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.waitingRoom.seatAvailable();
            this.haircutRoom.occupyBarberChair();
            System.out.println("Customer gets a hair cut");
        }
        else {
            this.barberSleeps();
        }
        this.haircutRoom.releaseBarberChair();
        this.barber.release();
        System.out.println("Barber is finished cutting hair");
 
    }
    public void run(){
        while(this.waitingRoom.aCustomerIsWaiting()){
            this.barberCutsHair();
        }
    }
}
