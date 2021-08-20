package Test7_Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
class MyServer{
    private ServerSocket server;
    private InputStream in;
    private OutputStream out;

    MyServer(int port) throws IOException{
        server =new ServerSocket(port);
        //System.out.println("listening");
        Socket socket = server.accept();
        in = socket.getInputStream();
        out = socket.getOutputStream();
        //System.out.println("connected!");
    }

    String getMassage() throws IOException {
        int len;
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1024];
//        while ((len = in.read(bytes)) ==1023) {
//            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
//        }
        len = in.read(bytes);
        sb.append(new String(bytes,0,len, StandardCharsets.UTF_8));
        //System.out.println("massage: "+sb);
        return sb.toString();
    }

    void sent(String massage) throws IOException {
        //System.out.println("sent massage: "+massage);
        out.write(massage.getBytes(StandardCharsets.UTF_8));

    }

    void close() throws IOException {
        server.close();
    }
}

public class NetServer1 {
    public static void main(String[] args) throws IOException{
        final String password = "su19891217su";
        MyServer server = new MyServer(7777);
        String massage = "Verifying Server!";
        server.sent(massage);
        //System.out.println("sented");
        for (int i = 0; i < 3; i++) {
            massage = server.getMassage();
            if (massage.equals(password)) {
                server.sent("Registration Successful!");
                break;
            } else {
                if (i == 2)
                    server.sent("Illegal User!");
                else
                    server.sent("PassWord Wrong!");

            }

        }
        server.close();
    }
}
