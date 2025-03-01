package org.example.ApacheThrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class OrderClient {
    private static final String SERVER_HOST = "192.168.30.102";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) {
        try (TTransport transport = new TSocket(SERVER_HOST, SERVER_PORT)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            OrderService.Client client = new OrderService.Client(protocol);
            String productId = "P2000";
            int quantity = 5;
            double total = client.calculateTotal(productId, quantity);

            System.out.println("Tổng tiền cho sản phẩm " + productId + " (x" + quantity + ") là: $" + total);
        } catch (Exception e) {
            System.err.println("Lỗi khi gọi Apache Thrift Server:");
            e.printStackTrace();
        }
    }
}
