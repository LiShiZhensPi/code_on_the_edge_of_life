package Test5_Thread;

public class ThreadTest1 {
    public static void main(String[] args){
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                int num=0;
                for(int i=0;i<=100;i++)
                    if(i%2==0){
                        num++;
                        System.out.println(num+"st even number: "+i);
                        yield();
                    }

            }
        };
        thread1.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                int num = 0;
                for(int i=0;i<=100;i++)
                    if(i%2==1){
                        num++;
                        System.out.println(num+"st odd number: "+i);
                        yield();
                    }
            }
        };
        thread2.start();

    }
}
