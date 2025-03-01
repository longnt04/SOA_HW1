package org.example.ApacheThrift;

import org.apache.thrift.TException;
import org.example.DatabaseManager;

public class OrderServiceImpl implements OrderService.Iface {
    @Override
    public double calculateTotal(String productId, int quantity) throws TException {
        double price = DatabaseManager.getProductPrice(productId);
        return price > 0 ? price * quantity : -1;
    }
}
