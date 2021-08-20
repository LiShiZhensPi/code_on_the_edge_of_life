package Test7_Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeChatServer {
    private ServerSocket server;
    private ArrayList<User> users;//用户列表
    private ArrayList<String> massages;//待发送消息队列
    private Listener listener;
    private MassageSenter massageSenter;


    class User{  //用户类，包含用户的登录id和一个输出流
        String name;
        OutputStream out;
        User(String name,OutputStream out){
            this.name = name;
            this.out = out;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static String GetMassage(InputStream in) throws IOException{//从一个输入流接收一个字符串
        int len;
        byte[] bytes = new byte[1024];
        len = in.read(bytes);
        return new String(bytes,0,len,StandardCharsets.UTF_8);
    }
    private void UserList(){  //列出当前在线用户，调试用
        for(User user : users)
            System.out.println(user);
    }

    class Listener extends Thread{ //监听线程类，负则监听是否有客户端连接
        @Override
        public void run() {
            try {
                while (true) {
                    Socket socket = server.accept();//此函数是阻塞的
                    InputStream in = socket.getInputStream();
                    String name = GetMassage(in);//获取接入用户的name
                    System.out.println(name +" has connected");
                    massages.add(name+" has joined just now!!");//向聊天室报告用户连入的信息
                    OutputStream out = socket.getOutputStream();
                    out.write("logined!".getBytes(StandardCharsets.UTF_8));//发送成功建立连接的反馈
                    User user = new User(name,out);
                    users.add(user);//添加至在线用户列表
                    MassageListener listener = new MassageListener(user,in);//创建用于接收此用户信息的线程
                    listener.start();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    class MassageListener extends Thread{ //接收线程类，用于从一个客户端接收信息，并加入待发送列表
        private User user;
        private InputStream in;
        MassageListener(User user,InputStream in){
            this.user = user;
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while (true){
                    String massage = GetMassage(in);
                    System.out.println("GET MASSAGE  "+massage);
                    if(massage.contains("//exit")){ //       "/exit" 是退出指令
                        break;
                    }
                    massages.add(massage);
                }//用户退出有两种形式，输入 “//exit” 或者直接关闭程序
                in.close();
                user.out.close();

            }catch (IOException e){//此异常是处理客户端异常关闭，即GetMassage(in)调用会抛出异常，因为in出入流已经自动关闭
                e.printStackTrace();
            }finally {
                System.out.println(user.name+" has exited!!");
                massages.add(user.name+" has exited!!");
                users.remove(user);//必须将已经断开连接的用户从用户列表中移除，否则会在发送信息时产生异常
                System.out.println("Now the users has");
                UserList();
            }

        }
    }
    private synchronized void SentToAll(String massage)throws IOException{//将信息发送给每一个用户，加入synchronized修饰，保证在发送时，用户列表不会被其他线程更改
        if(users.isEmpty())
            return;
        for(User user : users){
            user.out.write(massage.getBytes(StandardCharsets.UTF_8));
        }
    }

    class MassageSenter extends Thread{//消息发送线程

        @Override
        public void run() {
            while(true){
                try{
                    sleep(1);//此线程中没有阻塞的函数，加入沉睡语句防止线程过多抢占资源
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(!massages.isEmpty()){
                    String massage = massages.get(0);
                    massages.remove(0);
                    try {
                        SentToAll(massage);
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    WeChatServer(int port) throws IOException {  //初始化
        server = new ServerSocket(port);
        users = new ArrayList<>();
        massages = new ArrayList<>();
        listener = new Listener();
        massageSenter = new MassageSenter();
    }

    private void start(){ //线程启动
        listener.start();
        massageSenter.start();
    }

    public static void main(String[] args) throws IOException{
        WeChatServer server = new WeChatServer(7777);
        server.start();
    }

}


