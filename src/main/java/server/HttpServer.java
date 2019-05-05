package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpServer {

    public static void main(String[] args) throws IOException {

        // 启动服务器，监听8888端口
        ServerSocket httpService = new ServerSocket(8888);
        System.out.println("服务器启动，监听 " + 8888 + " 端口");
        while(!Thread.interrupted()){
            // 不停地接收客户端请求
            Socket client = httpService.accept();

            // 获取输入输出流
            InputStream ins = client.getInputStream();
            OutputStream out = client.getOutputStream();

            // 读取请求内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
            String line = reader.readLine();
            System.out.println(line);

            InputStream i = new FileInputStream("/Users/chendong/Desktop/thread/webRoot/index.html");
            PrintWriter pw = new PrintWriter(out);
            BufferedReader fr = new BufferedReader(new FileReader("/Users/chendong/Desktop/thread/webRoot/index.html"));
            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html;charset=utf-8");
            pw.println("Content-Length: " + i.available());
            pw.println("Server: hello");
            pw.println("Date: " + new Date());
            pw.println();
            pw.flush();
            String c = null;
            while((c = fr.readLine()) != null) {
                pw.print(c);
            }
            pw.flush();
            pw.close();
            fr.close();
            reader.close();
            client.close();

        }
        httpService.close();
    }

}
