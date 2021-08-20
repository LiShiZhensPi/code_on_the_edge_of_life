package Test7_Socket;

import java.io.IOException;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class MyClient {
    private Socket client;
    private InputStream in;
    private OutputStream out;

    MyClient(String host, int port) throws IOException {
        client = new Socket(host, port);
        in = client.getInputStream();
        out = client.getOutputStream();
        //System.out.println("connected");
    }

    String getMassage() throws IOException {
        int len;
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1024];
//        while ((len = in.read(bytes)) == 1023) {
//            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//        }
        len = in.read(bytes);
        sb.append(new String(bytes,0,len, StandardCharsets.UTF_8));
        //System.out.println("massage: "+sb);
        return sb.toString();
    }

    void sent(String massage) throws IOException {
        out.write(massage.getBytes(StandardCharsets.UTF_8));
    }

    void close() throws IOException {
        client.close();
    }
}


public class NetClient1 {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        MyClient client = new MyClient(host,7777);
        String massage;
        massage = client.getMassage();
        //System.out.println("get massage");
        if(!massage.equals("Verifying Server!")){
            System.out.println("Server Wrong!");
            System.exit(0);
        }

        do {
            if(massage.equals("PassWord Wrong"))
                System.out.println("PassWord Wrong");
            System.out.println("Please wrote password");
            Scanner scanner = new Scanner(System.in);
            massage = scanner.next();
            client.sent(massage);
            massage = client.getMassage();
        }while (massage.equals("PassWord Wrong!"));
        if(massage.equals("Illegal User!"))
            System.out.println("Illegal User!");
        else
            System.out.println("Registration Successful!");
        client.close();
    }
}
