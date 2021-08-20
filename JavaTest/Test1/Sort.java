package Test1;

import java.util.Arrays;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        int a[] = {12,34,9,-23,45,6,90,123,19,45,34};
        Arrays.sort(a);
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        int start = 0;
        int end = a.length-1;
        boolean flag = false;
        while(start<end){
            if(start+1 == end){
                if(start == n || end == n)
                    flag = true;
                break;
            }
            int mid = (start + end)/2;
            if(n == a[mid]){
                flag = true;
                break;
            }
            if(n < a[mid])
                end = mid;
            if(n > a[mid])
                start = mid;
        }
        if(flag)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
