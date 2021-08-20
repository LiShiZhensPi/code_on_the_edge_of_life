package Test7_Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeChatClient {  //WeChat的客户端类
    private Socket client;
    private String name;
    private InputStream in;
    private OutputStream out;
    private MassageSenter massageSenter;
    private MassageGeter massageGeter;
    class MassageGeter extends Thread{  //一个子线程类，用于客户端接收消息
        MassageGeter() throws IOException{
            in = client.getInputStream();
        }
        @Override
        public void run() {
            int len;
            byte[] bytes = new byte[1024];
            try {
                while ((len = in.read(bytes)) != -1) { //此函数是阻塞的
                    System.out.println(new String(bytes,0,len, StandardCharsets.UTF_8));
                }
            }catch (IOException e){
                System.out.println(e.toString());
            }
            System.out.println("Connection interruption");
        }
    }
    class MassageSenter extends Thread{  //一个子线程类，用于发送消息给服务器
        MassageSenter() throws IOException{
            out = client.getOutputStream();
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                while (scanner.hasNextLine()) { //此函数为阻塞的函数
                    String massage = scanner.nextLine();
                    out.write((name + " : " + massage).getBytes(StandardCharsets.UTF_8));
                    if(massage.equals("//exit"))
                        break;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    WeChatClient(String name, String host, int port) throws IOException {//初始化，实例化发送和接收2个线程
        this.name = name;
        client = new Socket(host,port);
        massageGeter = new MassageGeter();
        massageSenter = new MassageSenter();

    }

    void login() throws IOException{//登录时，先发送名字给服务器，在接收到服务器的正确回应之后，启动线程
        out.write(name.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = new byte[1024];
        int len;
        len = in.read(bytes);
        String answer = new String(bytes,0,len, StandardCharsets.UTF_8);
        if(answer.equals("logined!")) {
            System.out.println("Welcome to WeChat! "+name);
            massageSenter.start();
            massageGeter.start();
            try {
                massageSenter.join();//join()的作用是等线程结束之后再继续执行主线程(main)
                massageGeter.join();
            }catch (InterruptedException e){
                System.err.println(e.toString());
            }

        }else{
            System.out.println("Server Wrong");
        }
        client.close();
    }


    public static void main(String[] args) throws IOException{//程序入口
        String host = "127.0.0.1";
        WeChatClient client = new WeChatClient("Uzi",host,7777);
        client.login();
    }

}
