package org.example.gRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OrderClient {
    private static final String SERVER_HOST = "192.168.30.102";
    private static final int SERVER_PORT = 50051;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
        OrderServiceGrpc.OrderServiceBlockingStub stub = OrderServiceGrpc.newBlockingStub(channel);
        String productId = "P2000";
        int quantity = 5;
        OrderRequest request = OrderRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();

        OrderResponse response = stub.calculateTotal(request);

        System.out.println("Tổng tiền cho sản phẩm " + productId + " (x" + quantity + ") là: $" + response.getTotalPrice());
        channel.shutdown();
    }
}
