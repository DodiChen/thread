package server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServerThread implements Runnable {


    private static Map<String, String> contentMap = new HashMap<>();

    static {
        contentMap.put(".html", "text/html;charset=utf-8");
        contentMap.put(".jpg", "image/jpeg");
    }

    private Socket client;

    private InputStream ins;

    private OutputStream out;

    private PrintWriter pw;

    private BufferedReader br;


    public ServerThread(Socket client){
        this.client = client;
        init();
    }

    private void init(){
        // 获取输入输出流
        try {
            ins = client.getInputStream();
            out = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            go();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String webRoot = "/Users/chendong/Desktop/thread/webRoot";

    private void go() throws IOException {
        // 读取请求内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        String newline = reader.readLine();
        System.out.println(newline);
        String line = newline.split(" ")[1].replace("/", "/"); // 请求的资源
        if(line.equalsIgnoreCase("/")){
            line = line + "index.html";
        }
        System.out.println(line);

        InputStream i = new FileInputStream(webRoot + line);
        PrintWriter pw = new PrintWriter(out);
        //BufferedReader fr = new BufferedReader(new FileReader(webRoot + line));
        pw.println("HTTP/1.1 200 OK");
        pw.println("Content-Type: " + contentMap.get(line.substring(line.lastIndexOf("."), line.length())));
        pw.println("Content-Length: " + i.available());
        pw.println("Server: hello");
        pw.println("Date: " + new Date());
        pw.println();
        pw.flush();
        String c = null;
        byte[] buff = new byte[1024];
        int len = 0;
        while((len = i.read(buff)) != -1) {
            out.write(buff,0, len);
        }
        pw.flush();
        pw.close();
        i.close();
        reader.close();
        client.close();
    }
}
