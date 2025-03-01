package org.example.ApacheThrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class OrderServer {
    private static final int PORT = 9090;

    public static void main(String[] args) {
        try {
            OrderService.Processor<OrderServiceImpl> processor = new OrderService.Processor<>(new OrderServiceImpl());
            TServerTransport serverTransport = new TServerSocket(PORT);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Apache Thrift Server đang chạy trên cổng " + PORT + "...");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Đang tắt Apache Thrift Server...");
                server.stop();
                System.out.println("Apache Thrift Server đã tắt.");
            }));
            server.serve();
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi động Apache Thrift Server:");
            e.printStackTrace();
        }
    }
}
