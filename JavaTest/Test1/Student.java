package Test1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Student {
    static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/DD");
    String name;
    int age;
    Date birthday;
    double grade;
    Student(String namae,int age,Date birthday,double grade){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.grade = grade;
    }


    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[10];
        for(int i = 0;i<10;i++){
            System.out.println("Please write the "+i+"th student");
            String name = scanner.next();
            int age = scanner.nextInt();
            Date birthday = format.parse(scanner.next());
            double grade = scanner.nextDouble();
            students[i] = new Student(name,age,birthday,grade);
            System.out.println("successful");


        }
        double sumAge = 0;
        double sumGrade = 0;
        for(Student student:students){
            sumAge += student.age;
            sumGrade += student.grade;
        }
        double averageAge = sumAge/10;
        double averageGrade = sumGrade/10;
        System.out.println("Average of age is "+averageAge);
        System.out.println("Average of grade is "+averageGrade);
    }

}
