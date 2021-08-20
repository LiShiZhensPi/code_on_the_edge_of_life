package Test3_Exception;

import java.util.Scanner;

class xyzException extends Exception{
    public xyzException(){
        System.err.println("This is a Test3_Exception.xyzException");
    }
}

public class Exception2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try {
            String s = scanner.nextLine();
            System.out.println(s);
            if (s.equals("XYZ"))
                throw new xyzException();
        }catch (xyzException e){
            e.printStackTrace();
        }

    }

}
