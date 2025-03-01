package org.example.RMI;

import org.example.DatabaseManager;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class OrderServiceImpl extends UnicastRemoteObject implements OrderService {
    protected OrderServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculateTotal(String productId, int quantity) throws RemoteException {
        double price = DatabaseManager.getProductPrice(productId);
        if (price == -1) {
            System.out.println("Sản phẩm không tồn tại: " + productId);
            return -1;
        }
        return price * quantity;
    }
}
