package Test3_Exception;

import java.util.ArrayList;
import java.util.Scanner;

class NotDouble extends Exception{
    public NotDouble(){
        System.out.println("Mark must be double!!!");
    }
}

interface Average{
    double average();
}

class Student {
    private String name;
    double mark;
    public Student(String name,double mark){
        this.name = name;
        this.mark = mark;
    }
}


class Students {

    public ArrayList<Student> studentList = new ArrayList<>();
    public Students(int n){
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<n;i++){
            String name = "student."+i;
            System.out.println("Please write "+name+"' mark");
            Double d;
            try{
                if(scanner.hasNextDouble())
                    d = scanner.nextDouble();
                else
                    throw new NotDouble();
            }catch (NotDouble e){
                System.out.println("The mark will be 0.0");
                scanner.next();
                d = 0.0;
            }

            studentList.add(new Student(name,d));

        }
    }
}
class Grade1 implements Average{
    private Students students;
    public Grade1(int n){
        students = new Students(n);
    }
    @Override
    public double average() {
        double sum=0;
        for(Student s : students.studentList)
            sum += s.mark;
        return sum/students.studentList.size();
    }
}

class Grade2 implements Average{
    private Students students;
    public Grade2(int n){
        students = new Students(n);
    }
    @Override
    public double average() {
        int min=0,max=0;
        double Min = Double.MAX_VALUE;
        double Max = Double.MIN_VALUE;
        for(int i = 0; i<students.studentList.size(); i++){
            if(Min>students.studentList.get(i).mark){
                Min = students.studentList.get(i).mark;
                min = i;
            }
        }
        students.studentList.remove(min);
        for(int i = 0;i<students.studentList.size();i++){
            if(Min<students.studentList.get(i).mark){
                Max = students.studentList.get(i).mark;
                max = i;
            }
        }
        students.studentList.remove(max);
        double sum=0;
        for(Student s : students.studentList)
            sum += s.mark;
        return sum/students.studentList.size();
    }
}


public class Exception3 {
    public static void main(String[] args){
        Grade1 grade1 = new Grade1(5);
        System.out.println("Average1: "+grade1.average());
        Grade2 grade2 = new Grade2(5);
        System.out.println("Average2: "+grade2.average());
    }

}
