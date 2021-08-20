package Test6_IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentsWrite {
    public static void main(String[] args) {
        File file1 = new File("myfile1.txt");
        try {
            FileWriter fileWriter = new FileWriter(file1);
            Scanner scanner = new Scanner(System.in);
            String  number;
            String name;
            while(true){
                System.out.println("Please write Test3_Exception.Student");
                number = scanner.next();
                if(number.equals("bye"))
                    break;
                name = scanner.next();
                fileWriter.write(number+'-'+name+'\n');
            }
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e);
        }
        File file2 = new File("myfile2.txt");
        try{
            FileReader fileReader = new FileReader(file1);
            FileWriter fileWriter = new FileWriter(file2);
            char[] c = new char[1];
            int n = -1;
            while((n = fileReader.read(c))!=-1)
                fileWriter.write(c);
            System.out.println("Copy has overed");
            fileReader.close();
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e);
        }

    }
}
