package Test6_IO;

import java.io.*;
public class SecretExample {

    public static void main(String[] args){
        File fileone = new File("hello.txt");
        File filetwo = new File("hello.secret");
        char[] b = new char[100];
        try{
            FileReader in = new FileReader(fileone);
            FileWriter out = new FileWriter(filetwo);
            int n = -1;
            while((n = in.read(b))!=-1){
                for (int i = 0; i <n; i++) {
                    b[i] = (char)(b[i]^'a');
                }
                out.write(b);
            }
            out.close();
            in = new FileReader(filetwo);
            System.out.println("加密后的文件内容: ");
            while((n = in.read(b))!=-1){
                //System.out.println("n:"+n);
                String str = new String(b,0,n);
                System.out.print("asdfgfgfdg"+str);
                System.out.println();
            }
            in = new FileReader(filetwo);
            System.out.println("解密后的文件内容");
            while((n = in.read(b))!=-1){
                //System.out.println("n:"+n);
                for (int i = 0; i <n; i++) {
                    b[i] = (char)(b[i]^'a');
                }
                String str = new String(b,0,n);
                System.out.print(str);
            }
            in.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
