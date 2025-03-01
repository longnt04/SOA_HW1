package org.example.gRPC;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class OrderServer {
    private static final int PORT = 50051; // Cố định cổng gRPC

    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(PORT)
                .addService(new OrderServiceImpl()) // Đăng ký dịch vụ
                .build();

        try {
            server.start();
            System.out.println("gRPC Server đang chạy trên cổng " + PORT + "...");

            // Đảm bảo server tắt an toàn khi JVM dừng
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Đang tắt gRPC Server...");
                server.shutdown();
                System.out.println("gRPC Server đã tắt.");
            }));

            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            System.err.println("Lỗi khi khởi động gRPC Server:");
            e.printStackTrace();
        }
    }
}
