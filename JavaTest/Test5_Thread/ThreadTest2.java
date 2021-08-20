package Test5_Thread;

class Tortoise extends Thread{
    int sleepTime = 0,liveTime = 0;
    public Tortoise(String name,int sleepTime,int liveTime){
        this.sleepTime = sleepTime;
        this.liveTime = liveTime;
        this.setName(name);
    }

    @Override
    public void run() {
        while(true){
            liveTime--;
            System.out.println("@_@");
            try{
                sleep(sleepTime);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(liveTime<=0){
                System.out.println(getName()+" has dead!\n");
                break;
            }
        }
    }
}

class Rabit extends Thread{
    int sleepTime = 0,liveTime = 0;
    public Rabit(String name,int sleepTime,int liveTime){
        super(name);
        this.sleepTime = sleepTime;
        this.liveTime = liveTime;
    }

    @Override
    public void run() {
        while(true){
            liveTime--;
            System.out.println("*_*");
            try{
                sleep(sleepTime);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if(liveTime<=0) {
                System.out.println(getName()+" has dead!");
                break;
            }
        }
    }
}


public class ThreadTest2 {
    public static void main(String[] args){
        Rabit rabit = new Rabit("lishizhen",1,15);
        Tortoise tortoise = new Tortoise("GuaPi",2,30);
        rabit.start();
        tortoise.start();
    }
}
