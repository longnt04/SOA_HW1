package org.example.gRPC;

import io.grpc.stub.StreamObserver;
import org.example.DatabaseManager;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void calculateTotal(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        double price = DatabaseManager.getProductPrice(request.getProductId());
        double total = price > 0 ? price * request.getQuantity() : -1;

        OrderResponse response = OrderResponse.newBuilder()
                .setTotalPrice(total)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
