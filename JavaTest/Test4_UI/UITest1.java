package Test4_UI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UITest1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("显示整数数字");
        JLabel integer = new JLabel("整数");
        JLabel hundred = new JLabel("百位");
        JLabel ten = new JLabel("十位");
        JLabel bit = new JLabel("个位");
        JTextField integerFiled = new JTextField();
        JTextField hundredFiled = new JTextField();
        JTextField tenFiled = new JTextField();
        JTextField bitFiled = new JTextField();
        integerFiled.getDocument().addDocumentListener(new DocumentListener() {
            public  void change(){
                String value = integerFiled.getText();
                try {
                    int val = Integer.valueOf(value);
                    int hundred = val/100;
                    int ten = val%100/10;
                    int bit = val%10;
                    hundredFiled.setText(String.valueOf(hundred));
                    tenFiled.setText(String.valueOf(ten));
                    bitFiled.setText(String.valueOf(bit));
                }catch (NumberFormatException e){
                    JFrame Error = new JFrame("Error");
                    JLabel massage = new JLabel(value+"不能换成整数，请重新输入！");
                    JButton yes = new JButton("Yes");
                    yes.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            integerFiled.setText("");
                            Error.dispose();
                        }
                    });
                    Error.setLayout(new GridLayout(2,1));
                    Error.add(massage);
                    Error.add(yes);
                    Error.pack();
                    Error.setVisible(true);
                }

            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                change();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(integer);
        frame.add(integerFiled);
        frame.add(hundred);
        frame.add(hundredFiled);
        frame.add(ten);
        frame.add(tenFiled);
        frame.add(bit);
        frame.add(bitFiled);
        frame.setSize(500,300);
        frame.setLayout(new GridLayout(4,2));
        frame.setVisible(true);
    }
}
