package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer2 {

    public static void main(String[] args) throws IOException {


        ExecutorService pool = Executors.newCachedThreadPool();

        // 启动服务器，监听8888端口
        ServerSocket httpService = new ServerSocket(8888);
        System.out.println("服务器启动，监听 " + 8888 + " 端口");
        while(!Thread.interrupted()) {
            // 不停地接收客户端请求
            Socket client = httpService.accept();
            //new Thread(new ServerThread(client)).start();
            pool.execute(new ServerThread(client));
        }
        httpService.close();
    }

}
