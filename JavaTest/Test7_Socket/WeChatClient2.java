package Test7_Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeChatClient2 {
    private Socket client;
    private String name;
    private InputStream in;
    private OutputStream out;
    private MassageSenter massageSenter;
    private MassageGeter massageGeter;
    class MassageGeter extends Thread{
        MassageGeter() throws IOException{
            in = client.getInputStream();
        }
        @Override
        public void run() {
            int len;
            byte[] bytes = new byte[1024];
            try {
                while ((len = in.read(bytes)) != -1) {
                    System.out.println(new String(bytes,0,len, StandardCharsets.UTF_8));
                }
            }catch (IOException e){
                System.out.println(e.toString());
            }
            System.out.println("Connection interruption");
        }
    }
    class MassageSenter extends Thread{
        MassageSenter() throws IOException{
            out = client.getOutputStream();
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                while (scanner.hasNextLine()) {
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

    WeChatClient2(String name, String host, int port) throws IOException {
        this.name = name;
        client = new Socket(host,port);
        massageGeter = new MassageGeter();
        massageSenter = new MassageSenter();

    }

    void login() throws IOException{
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
                massageSenter.join();
                massageGeter.join();
            }catch (InterruptedException e){
                System.err.println(e.toString());
            }

        }else{
            System.out.println("Server Wrong");
        }
        client.close();
    }


    public static void main(String[] args) throws IOException{
        String host = "127.0.0.1";
        WeChatClient2 client = new WeChatClient2("Rokkie",host,7777);
        client.login();
    }

}
