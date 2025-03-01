package org.example.RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class OrderClient {
    private static final String SERVER_HOST = "192.168.30.102";
    private static final int SERVER_PORT = 1099;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, SERVER_PORT);
            OrderService orderService = (OrderService) registry.lookup("OrderService");
            String productId = "P2000";
            int quantity = 5;
            double total = orderService.calculateTotal(productId, quantity);

            System.out.println("Tổng tiền cho sản phẩm " + productId + " (x" + quantity + ") là: $" + total);
        } catch (Exception e) {
            System.err.println("Lỗi khi gọi RMI Server:");
            e.printStackTrace();
        }
    }
}
