package Test2;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.io.FileWriter;
import java.util.zip.DataFormatException;

class DateException extends Exception{

}
public class MyDate {
    private int year;
    private int month;
    private int day;
    MyDate(int year,int month,int day) throws DateException {
        this.year = year;
        this.month = month;
        this.day = day;
        boolean IfLeap;
        if(year % 100 == 0)
            if(year % 400 ==0)
                IfLeap = true;
            else
                IfLeap = false;
        else if(year % 4 == 0)
            IfLeap = true;
        else
            IfLeap = false;

        if(month == 2) {
            if (IfLeap)
                if (day > 29 || day < 1) {
                    throw new DateException();
                }
            else if (day>28 ||day <1)
                throw new DateException();
        }
        else if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12) {
            if (day > 31 || day < 1)
                throw new DateException();
        }
        else if(month == 4||month == 6||month == 9||month == 11) {
            if (day > 30 || day < 1)
                throw new DateException();
        }
        else
            throw new DateException();

    }

    public static void main(String[] args) throws DateException{
        new MyDate(1999,0,44);
    }
}
