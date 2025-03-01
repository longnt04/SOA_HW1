package org.example.RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class OrderServer {
    public static void main(String[] args) {
        try {
            OrderServiceImpl orderService = new OrderServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("OrderService", orderService);
            System.out.println("OrderServer is running...");
        } catch (RemoteException e) {
            System.err.println("OrderServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

