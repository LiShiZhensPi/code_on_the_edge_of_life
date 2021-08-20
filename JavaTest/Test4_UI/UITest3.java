package Test4_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class UITest3 {
    class Student{
        String name;
        String sex;
        int age;
        Date birth;
        double grade;
        Student(String name,String sex,int age,Date birth,double grade){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.birth = birth;
            this.grade = grade;
        }
    }
    JFrame frame = new JFrame("student");
    JTextField name = new JTextField();
    JTextField sex = new JTextField();
    JTextField age = new JTextField();
    JTextField birth = new JTextField();
    JTextField grade = new JTextField();
    JLabel number = new JLabel("输入第 1 名同学的信息");
    JButton button = new JButton("输入");
    JButton display = new JButton("显示");
    ArrayList<Student> students = new ArrayList<>();
    int num = 1;
    public void InitFrame(){
        frame.setLayout(new GridLayout(7,2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel("name"));
        frame.add(name);
        frame.add(new JLabel("sex"));
        frame.add(sex);
        frame.add(new JLabel("age"));
        frame.add(age);
        frame.add(new JLabel("birth"));
        frame.add(birth);
        frame.add(new JLabel("grade"));
        frame.add(grade);
        frame.add(button);
        frame.add(display);
        frame.add(number);
        frame.pack();
        frame.setVisible(true);

    }
    void Display(){
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.grade, o1.grade);
            }
        });
        for(Student s : students)
            System.out.println(s.name + " : "+s.grade);
    }

    UITest3(){
        InitFrame();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    students.add(new Student(name.getText(), sex.getText(), Integer.valueOf(age.getText()), format.parse(birth.getText()), Double.valueOf(grade.getText())));
                }catch (ParseException exc){
                    exc.printStackTrace();
                }
                num++;
                number.setText("输入第名 "+num+" 学生的信息");
            }
        });
        display.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display();
            }
        });
    }

    public static void main(String[] args) {
        UITest3 uiTest3 = new UITest3();

    }


}
