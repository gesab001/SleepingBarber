package BarberShop;

import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Sleeping Barber Algorithm implementation
 *
 * @author Giovanni Saberon
 */

public class Main {

    public static void main(String[] args) {
        Semaphore barber = new Semaphore(1);
        Semaphore waitingRoom_chairs = new Semaphore(3);
        Semaphore barber_chair = new Semaphore(1);
        
        WaitingRoom waitingRoom = new WaitingRoom(waitingRoom_chairs);
        HaircutRoom haircutRoom = new HaircutRoom(barber, barber_chair);
 
        Barber sleepingBarber = new Barber(barber, waitingRoom, haircutRoom){};
        


//        Thread customerThread = new Thread(customer);
        Thread sleepingBarberThread = new Thread(sleepingBarber);
        sleepingBarberThread.start();
        Thread[] pts = new Thread[10];
	for(int i = 0; i < 10; i++) {
            Customer customer = new Customer(sleepingBarber, waitingRoom, haircutRoom){}; 
            pts[i] = new Thread(customer);
            pts[i].start();
        }
    }
}
