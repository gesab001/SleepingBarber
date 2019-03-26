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
public class Customer implements Runnable {
    Barber barber;
    Semaphore barber_chair;
    WaitingRoom waitingRoom;
    HaircutRoom haircutRoom;
    
    public Customer(Barber barber, WaitingRoom waitingRoom, HaircutRoom haircutRoom){
        this.barber = barber;
        this.waitingRoom = waitingRoom;
        this.haircutRoom = haircutRoom;
    }
  
    public void customerTakesASeatInTheWaitingRoom(){
        System.out.println("Customer takes a seat in the waiting room");
        waitingRoom.seatOccupied();
       
    }
    
    public void customerLeavesBarberShopBecauseNoSeatsInWaitingRoom(){
        System.out.println("Customer leaves barber shop because waiting room is full");

    }
    public void customerWakesUpBarber(){
        if(barber.isSleeping() || barber.notBusy()){
            System.out.println("Customer wakes up barber");
            barber.barberCutsHair();
        }else{
            this.customerFindSeatsInWaitingRoom();
        };
    }
    public void customerLeavesTheWaitingRoom(){
        waitingRoom.seatAvailable();
    }
    
    public void customerFindSeatsInWaitingRoom(){
        boolean waitingRoomIsFull = waitingRoom.isWaitingRoomFull();
        if (waitingRoomIsFull){
            this.customerLeavesBarberShopBecauseNoSeatsInWaitingRoom();
           
        }
        else{
             System.out.println("Waiting room is not full");
             this.customerTakesASeatInTheWaitingRoom();
        }
    }
    public void run(){
        this.customerWakesUpBarber();
        
        
    }

 
}
